package com.fiap_pedido_service.core.usecase;

import com.fiap_pedido_service.core.domain.Pedido;

public interface ProcessaPedidoUseCase {
    void processaPedido(Pedido pedido);
}
