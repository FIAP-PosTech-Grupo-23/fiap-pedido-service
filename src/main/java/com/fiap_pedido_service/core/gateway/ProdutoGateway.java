package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.core.domain.Produto;

import java.util.List;

public interface ProdutoGateway {
    List<Produto> obtemDadosProdutos(List<String> sku);
}
