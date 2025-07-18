package com.fiap_pedido_service.gateway;

import com.fiap_pedido_service.domain.Pedido;
import com.fiap_pedido_service.domain.StatusEnum;

public interface PedidoGateway {
    void salvaPedido(Pedido pedido);

    Pedido recuperaPedidoPorIdPagamento(Long idPagamento);

    void atualizaStatus(Long idPagamento, StatusEnum status);
}
