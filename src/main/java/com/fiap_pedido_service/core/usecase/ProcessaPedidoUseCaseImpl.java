package com.fiap_pedido_service.core.usecase;

import com.fiap_pedido_service.core.gateway.*;
import com.fiap_pedido_service.domain.Cliente;
import com.fiap_pedido_service.domain.Produto;
import com.fiap_pedido_service.domain.pedido.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        List<String> skus = pedido.getProdutos().stream().map(Produto::getSku).toList();

        List<Produto> produtosCompletos = produtoGateway.obtemDadosProdutos(skus);

        Cliente cliente = clienteGateway.obtemDadosCliente(pedido.getIdCliente());

        Map<String, Integer> mapSkuProdutoPorQuantidade = pedido.getProdutos().stream()
                .collect(Collectors.toMap(Produto::getSku, Produto::getQuantidade));

        Map<String, BigDecimal> mapSkuProdutoPorPreco = produtosCompletos.stream()
                .collect(Collectors.toMap(Produto::getSku, Produto::getPreco));

        estoqueGateway.baixaEstoque(mapSkuProdutoPorQuantidade);

        BigDecimal valorTotal = pedido.getProdutos().stream()
                .map(p -> {
                    BigDecimal valorUnitario = mapSkuProdutoPorPreco.get(p.getSku());
                    BigDecimal valorDoProduto = valorUnitario.multiply(BigDecimal.valueOf(p.getQuantidade()));
                    return valorDoProduto;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pagamentoGateway.solicitaPagamento(valorTotal,
                pedido.getPagamento(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco());

        pedidoGateway.salvaPedido(pedido);

    }
}
