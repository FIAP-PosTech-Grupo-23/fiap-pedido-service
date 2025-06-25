package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.core.domain.StatusEnum;
import com.fiap_pedido_service.core.domain.Pedido;

public interface PedidoGateway {
    void salvaPedido(Pedido pedido);

    Pedido recuperaPedidoPorIdPagamento(Long idPagamento);

    void atualizaStatus(Long idPagamento, StatusEnum status);
}
