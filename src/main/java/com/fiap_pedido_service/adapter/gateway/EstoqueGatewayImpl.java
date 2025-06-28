package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.adapter.client.EstoqueClient;
import com.fiap_pedido_service.core.gateway.EstoqueGateway;
import com.fiap_pedido_service.core.domain.Estoque;
import com.fiap_pedido_service.core.domain.EstoqueEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstoqueGatewayImpl implements EstoqueGateway {

    private final EstoqueClient estoqueClient;

    @Override
    public List<Estoque> baixaEstoque(List<Estoque> estoques) {

//        List<Estoque> estoques1 = estoqueClient.baixaEstoque(estoques);

        Estoque e1 = new Estoque(1L, 1L, 10, EstoqueEnum.DISPONIVEL);

        return List.of(e1);


    }
}
