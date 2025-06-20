package com.fiap_pedido_service.core.usecase;

import com.fiap_pedido_service.core.gateway.*;
import com.fiap_pedido_service.domain.Cliente;
import com.fiap_pedido_service.domain.pedido.Pedido;
import com.fiap_pedido_service.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProcessaPedidoUseCaseImpl implements ProcessaPedidoUseCase{

    private final PedidoGateway pedidoGateway;
    private final ProdutoGateway produtoGateway;
    private final ClienteGateway clienteGateway;
    private final EstoqueGateway estoqueGateway;
    private final PagamentoGateway pagamentoGateway;

    @Override
    public void processaPedido(Pedido pedido) {

        pedido.abrePedido();

        Produto produto = produtoGateway.obtemDadosProduto(pedido.getSku());

        Cliente cliente = clienteGateway.obtemDadosCliente(pedido.getIdCliente());

        estoqueGateway.baixaEstoque(produto.getId(), pedido.getQuantidade());

        BigDecimal valorTotal = produto.getPreco()
                .multiply(BigDecimal.valueOf(pedido.getQuantidade()));

        pagamentoGateway.solicitaPagamento(valorTotal,
                Objects.nonNull(pedido.getCartaoCredito()) ? pedido.getCartaoCredito(): pedido.getPix(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco());

        pedidoGateway.salvaPedido(pedido);




    }
}
