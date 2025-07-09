package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.adapter.client.ClienteClient;
import com.fiap_pedido_service.adapter.json.ClienteDTO;
import com.fiap_pedido_service.core.domain.Cliente;
import com.fiap_pedido_service.core.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteClient client;

    @Override
    public Cliente obtemDadosCliente(UUID idCliente) {

        ClienteDTO clienteDTO = client.getCliente(idCliente);

        log.info("Dados do Cliente obtidos: {}", clienteDTO);

        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getEndereco());

    }
}
