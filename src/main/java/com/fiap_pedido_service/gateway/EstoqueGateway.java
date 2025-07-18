package com.fiap_pedido_service.gateway;

import com.fiap_pedido_service.domain.Estoque;

import java.util.List;

public interface EstoqueGateway {
    List<Estoque> baixaEstoque(List<Estoque> estoques);
}
