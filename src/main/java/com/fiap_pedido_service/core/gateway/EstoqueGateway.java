package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.domain.Estoque;

import java.util.List;

public interface EstoqueGateway {
    void baixaEstoque(List<Estoque> estoques);
}
