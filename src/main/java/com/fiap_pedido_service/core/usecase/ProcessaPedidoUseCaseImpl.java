package com.fiap_pedido_service.core.usecase;

import com.fiap_pedido_service.core.domain.*;
import com.fiap_pedido_service.core.gateway.*;
import com.fiap_pedido_service.core.domain.pedido.Pagamento;
import com.fiap_pedido_service.core.domain.pedido.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

        List<String> skus = produtosRequest.stream().map(Produto::getSku).toList();

        List<Produto> produtosBanco = produtoGateway.obtemDadosProdutos(skus);

        Cliente cliente = clienteGateway.obtemDadosCliente(pedido.getIdCliente());

        Map<String, BigDecimal> mapSkuProdutoPorPreco = produtosBanco.stream()
                .collect(Collectors.toMap(Produto::getSku, Produto::getPreco));

        List<Estoque> estoques = processaBaixaEstoque(mapSkuProdutoRequestPorQuantidade, produtosBanco);

        BigDecimal valorTotal = calculaValorTotal(produtosRequest, mapSkuProdutoPorPreco);

        if (estoqueIndisponivel(estoques)) {
            salvaPedidoSemPagamento(pedido, valorTotal);
            return;
        }

        Long idPagamento = pagamentoGateway.solicitaPagamento(valorTotal,
                pedido.getPagamento(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco());

        Pedido pedidoCompleto = new Pedido(
                pedido.getIdCliente(),
                pedido.getProdutos(),
                new Pagamento(idPagamento),
                pedido.getStatusEnum(),
                valorTotal);

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

    private BigDecimal calculaValorTotal(List<Produto> produtosRequest, Map<String, BigDecimal> mapSkuProdutoPorPreco) {
        return produtosRequest.stream()
                .map(p ->
                        multiplicaValorUnitarioPorQuantidade(p, mapSkuProdutoPorPreco)
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static BigDecimal multiplicaValorUnitarioPorQuantidade(Produto p, Map<String, BigDecimal> mapSkuProdutoPorPreco) {
        return mapSkuProdutoPorPreco.get(p.getSku())
                .multiply(BigDecimal.valueOf(p.getQuantidade()));
    }

    private List<Estoque> processaBaixaEstoque(Map<String, Integer> mapSkuProdutoRequestPorQuantidade, List<Produto> produtosBanco) {
        List<Estoque> inputEstoques = montaEstoques(mapSkuProdutoRequestPorQuantidade, produtosBanco);
        return estoqueGateway.baixaEstoque(inputEstoques);
    }

    private boolean estoqueIndisponivel(List<Estoque> estoques) {
        return estoques.stream().anyMatch(e -> e.getEstoqueEnum() == EstoqueEnum.INDISPONIVEL);
    }

    private void salvaPedidoSemPagamento(Pedido pedido, BigDecimal valorTotal) {
        Pedido pedidoSemEstoque = new Pedido(
                pedido.getIdCliente(),
                pedido.getProdutos(),
                StatusEnum.FECHADO_SEM_ESTOQUE,
                valorTotal);
        pedidoGateway.salvaPedido(pedidoSemEstoque);
    }
}
