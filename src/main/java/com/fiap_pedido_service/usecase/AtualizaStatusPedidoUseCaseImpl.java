package com.fiap_pedido_service.usecase;

import com.fiap_pedido_service.domain.*;
import com.fiap_pedido_service.gateway.EstoqueGateway;
import com.fiap_pedido_service.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtualizaStatusPedidoUseCaseImpl implements AtualizaStatusPedidoUseCase{

    private final PedidoGateway pedidoGateway;
    private final EstoqueGateway estoqueGateway;

    @Override
    public void atualizaStatusPedido(PedidoStatusPagamento pedidoStatusPagamento) {

        StatusPagamentoEnum statusPagamentoEnum = pedidoStatusPagamento.getStatusPagamento();
        StatusEnum status = statusPagamentoEnum.getStatusInterno();

        Pedido pedido = pedidoGateway.recuperaPedidoPorIdPagamento(pedidoStatusPagamento.getIdPagamento());

        if(status.equals(StatusEnum.FECHADO_COM_SUCESSO)){

            pedidoGateway.atualizaStatus(pedidoStatusPagamento.getIdPagamento(), status);

        }else{

            List<Estoque> estoques = pedido.getProdutos().stream()
                    .map(produto ->
                new Estoque(produto.getId(), produto.getQuantidade() * -1)
            ).toList();

            pedidoGateway.atualizaStatus(pedidoStatusPagamento.getIdPagamento(), status);

            estoqueGateway.baixaEstoque(estoques);

        }

    }
}
