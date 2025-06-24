package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.core.gateway.EstoqueGateway;
import com.fiap_pedido_service.domain.Estoque;
import com.fiap_pedido_service.domain.EstoqueEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueGatewayImpl implements EstoqueGateway {
    @Override
    public List<Estoque> baixaEstoque(List<Estoque> estoques) {

        Estoque e1 = new Estoque(1L, 1L, 10, EstoqueEnum.INDISPONIVEL);

        return List.of(e1);


    }
}
