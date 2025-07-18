package com.fiap_pedido_service.usecase;

import com.fiap_pedido_service.domain.*;
import com.fiap_pedido_service.gateway.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessaPedidoUseCaseImpl implements ProcessaPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final ProdutoGateway produtoGateway;
    private final ClienteGateway clienteGateway;
    private final EstoqueGateway estoqueGateway;
    private final PagamentoGateway pagamentoGateway;

    @Override
    public void processaPedido(Pedido pedido) {

        List<Produto> produtosRequest = pedido.getProdutos();

        Map<String, Integer> mapSkuProdutoRequestPorQuantidade = produtosRequest.stream()
                .collect(Collectors.toMap(Produto::getSku, Produto::getQuantidade));

        Set<String> skus = mapSkuProdutoRequestPorQuantidade.keySet();

        List<Produto> produtosBanco = produtoGateway.obtemDadosProdutos(skus);

        if (produtosBanco.isEmpty()) {
            salvaPedidoSemProduto(pedido);
            return;
        }

        List<Produto> produtosBancoComQuantidadeDoPedido = produtosBanco.stream()
                .map(p ->
                        new Produto(p.getId(),
                                p.getSku(),
                                p.getNome(),
                                p.getDescricao(),
                                p.getPreco(),
                                p.getCriadoEm(),
                                p.getAtualizadoEm(),
                                mapSkuProdutoRequestPorQuantidade.get(p.getSku()))
                ).toList();

        Pedido pedidoComProduto = new Pedido(
                produtosBancoComQuantidadeDoPedido,
                pedido.getPagamento(),
                pedido.getStatusEnum()
        );

        Cliente cliente;
        try {
            cliente = clienteGateway.obtemDadosCliente(pedido.getCliente().getId());
        } catch (Exception e) {
            salvaPedidoSemCliente(pedidoComProduto);
            return;
        }

        List<Estoque> estoques = processaBaixaEstoque(mapSkuProdutoRequestPorQuantidade, produtosBancoComQuantidadeDoPedido);

        if (estoqueIndisponivel(estoques)) {
            salvaPedidoSemEstoque(pedidoComProduto, cliente);
            return;
        }

        Long idPagamento = pagamentoGateway.solicitaPagamento(pedidoComProduto.getValorTotal(),
                pedidoComProduto.getPagamento(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco());

        Pedido pedidoCompleto = new Pedido(
                cliente,
                pedidoComProduto.getProdutos(),
                new Pagamento(idPagamento),
                pedidoComProduto.getStatusEnum(),
                pedidoComProduto.getValorTotal());

        pedidoGateway.salvaPedido(pedidoCompleto);

    }

    private List<Estoque> montaEstoques(Map<String, Integer> mapSkuProdutoRequestPorQuantidade, List<Produto> produtosBanco) {
        List<Estoque> estoques = new ArrayList<>();

        produtosBanco.forEach(p -> {
            Estoque estoque = new Estoque(p.getId(), mapSkuProdutoRequestPorQuantidade.get(p.getSku()));
            estoques.add(estoque);
        });

        return estoques;

    }

    private List<Estoque> processaBaixaEstoque(Map<String, Integer> mapSkuProdutoRequestPorQuantidade, List<Produto> produtosBanco) {
        List<Estoque> inputEstoques = montaEstoques(mapSkuProdutoRequestPorQuantidade, produtosBanco);
        return estoqueGateway.baixaEstoque(inputEstoques);
    }

    private boolean estoqueIndisponivel(List<Estoque> estoques) {
        return estoques.stream().anyMatch(Estoque::isEstoqueIndisponivel);
    }

    private void salvaPedidoSemEstoque(Pedido pedido, Cliente cliente) {
        Pedido pedidoSemEstoque = new Pedido(
                cliente,
                pedido.getProdutos(),
                StatusEnum.FECHADO_SEM_ESTOQUE,
                pedido.getValorTotal());
        pedidoGateway.salvaPedido(pedidoSemEstoque);
    }

    private void salvaPedidoSemProduto(Pedido pedido) {
        Pedido pedidoSemProduto = new Pedido(
                pedido.getPagamento(),
                StatusEnum.FECHADO_SEM_PRODUTO
        );
        pedidoGateway.salvaPedido(pedidoSemProduto);
    }

    private void salvaPedidoSemCliente(Pedido pedido) {
        Pedido pedidoSemCliente = new Pedido(
                pedido.getProdutos(),
                pedido.getPagamento(),
                StatusEnum.FECHADO_SEM_CLIENTE
        );
        pedidoGateway.salvaPedido(pedidoSemCliente);
    }
}
