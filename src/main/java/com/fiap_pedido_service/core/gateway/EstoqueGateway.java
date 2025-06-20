package com.fiap_pedido_service.core.gateway;

import java.util.Map;

public interface EstoqueGateway {
    void baixaEstoque(Map<Long, Integer> mapIdProdutoPorQuantidade);
}
