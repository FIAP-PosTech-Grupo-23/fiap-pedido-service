package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.domain.Produto;

public interface ProdutoGateway {
    Produto obtemDadosProduto(String sku);
}
