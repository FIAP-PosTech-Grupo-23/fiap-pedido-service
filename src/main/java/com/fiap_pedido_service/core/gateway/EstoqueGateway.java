package com.fiap_pedido_service.core.gateway;

public interface EstoqueGateway {
    void baixaEstoque(Long idProduto, int quantidade);
}
