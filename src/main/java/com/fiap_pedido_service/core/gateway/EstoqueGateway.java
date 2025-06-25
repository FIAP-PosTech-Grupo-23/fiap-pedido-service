package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.core.domain.Estoque;

import java.util.List;

public interface EstoqueGateway {
    List<Estoque> baixaEstoque(List<Estoque> estoques);
}
