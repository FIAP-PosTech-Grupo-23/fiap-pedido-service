package com.fiap_pedido_service.usecase;

import com.fiap_pedido_service.domain.PedidoStatusPagamento;

public interface AtualizaStatusPedidoUseCase {
    void atualizaStatusPedido(PedidoStatusPagamento pedidoStatusPagamento);
}
