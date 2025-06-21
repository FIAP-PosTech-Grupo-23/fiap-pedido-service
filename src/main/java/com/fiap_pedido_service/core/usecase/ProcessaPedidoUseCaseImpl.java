package com.fiap_pedido_service.core.usecase;

import com.fiap_pedido_service.core.gateway.*;
import com.fiap_pedido_service.domain.Cliente;
import com.fiap_pedido_service.domain.Estoque;
import com.fiap_pedido_service.domain.Produto;
import com.fiap_pedido_service.domain.pedido.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        Map<String, Integer> mapSkuProdutoPorQuantidade = produtosRequest.stream()
                .collect(Collectors.toMap(Produto::getSku, Produto::getQuantidade));

        List<String> skus = produtosRequest.stream().map(Produto::getSku).toList();

        List<Produto> produtosBanco = produtoGateway.obtemDadosProdutos(skus);

        Cliente cliente = clienteGateway.obtemDadosCliente(pedido.getIdCliente());

        Map<String, BigDecimal> mapSkuProdutoPorPreco = produtosBanco.stream()
                .collect(Collectors.toMap(Produto::getSku, Produto::getPreco));

        List<Estoque> inputEstoques = montaRequestEstoques(mapSkuProdutoPorQuantidade, produtosBanco);

        estoqueGateway.baixaEstoque(inputEstoques);

        BigDecimal valorTotal = calculaValorTotal(produtosRequest, mapSkuProdutoPorPreco);

        pagamentoGateway.solicitaPagamento(valorTotal,
                pedido.getPagamento(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco());

        pedidoGateway.salvaPedido(pedido);

    }

    private List<Estoque> montaRequestEstoques(Map<String, Integer> mapSkuProdutoPorQuantidade, List<Produto> produtosBanco) {
        List<Estoque> estoques = new ArrayList<>();

        produtosBanco.forEach(p -> {
            Estoque estoque = new Estoque(p.getId(), mapSkuProdutoPorQuantidade.get(p.getSku()));
            estoques.add(estoque);
        });

        return estoques;

    }

    private static BigDecimal calculaValorTotal(List<Produto> produtosRequest, Map<String, BigDecimal> mapSkuProdutoPorPreco) {
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
}
