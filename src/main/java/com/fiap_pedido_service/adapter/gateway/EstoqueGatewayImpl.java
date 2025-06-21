package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.core.gateway.EstoqueGateway;
import com.fiap_pedido_service.domain.Estoque;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueGatewayImpl implements EstoqueGateway {
    @Override
    public void baixaEstoque(List<Estoque> estoques) {

    }
}
