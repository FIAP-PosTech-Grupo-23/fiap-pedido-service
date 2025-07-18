package com.fiap_pedido_service.gateway;

import com.fiap_pedido_service.gateway.client.EstoqueClient;
import com.fiap_pedido_service.controller.json.EstoqueDTO;
import com.fiap_pedido_service.domain.Estoque;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EstoqueGatewayImpl implements EstoqueGateway {

    private final EstoqueClient estoqueClient;

    @Override
    public List<Estoque> baixaEstoque(List<Estoque> estoques) {

        List<EstoqueDTO> estoquesRequestDTO = estoques.stream().map(estoque ->
                new EstoqueDTO(estoque.getIdProduto(), estoque.getQuantidade())
        ).toList();

        log.info("Realizando chamada para estoques com request: {}", estoquesRequestDTO);

        List<EstoqueDTO> estoquesDTO = estoqueClient.baixaEstoque(estoquesRequestDTO);

        log.info("Retorno estoques: {}", estoquesDTO);

        return estoquesDTO.stream().map(estoque ->
                new Estoque(estoque.getIdProduto(), estoque.getQuantidade(), estoque.getStatus())
        ).toList();

    }
}
