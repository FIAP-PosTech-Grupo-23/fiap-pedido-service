package com.fiap_pedido_service.core.usecase;

import com.fiap_pedido_service.core.domain.PedidoStatusPagamento;

public interface AtualizaStatusPedidoUseCase {
    void atualizaStatusPedido(PedidoStatusPagamento pedidoStatusPagamento);
}
