package com.fiap_pedido_service.core.usecase;

import com.fiap_pedido_service.core.domain.pedido.Pedido;

public interface ProcessaPedidoUseCase {
    void processaPedido(Pedido pedido);
}
