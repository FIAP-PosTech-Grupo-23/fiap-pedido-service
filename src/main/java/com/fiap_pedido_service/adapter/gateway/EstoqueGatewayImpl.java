package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.core.gateway.EstoqueGateway;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EstoqueGatewayImpl implements EstoqueGateway {
    @Override
    public void baixaEstoque(Map<String, Integer> mapSkuProdutoPorQuantidade) {

    }
}
