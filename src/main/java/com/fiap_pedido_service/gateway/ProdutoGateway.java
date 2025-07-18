package com.fiap_pedido_service.gateway;

import com.fiap_pedido_service.domain.Produto;

import java.util.List;
import java.util.Set;

public interface ProdutoGateway {
    List<Produto> obtemDadosProdutos(Set<String> sku);
}
