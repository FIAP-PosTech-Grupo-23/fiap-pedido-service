package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.domain.pedido.Pedido;

public interface PedidoGateway {
    void salvaPedido(Pedido pedido);
}
