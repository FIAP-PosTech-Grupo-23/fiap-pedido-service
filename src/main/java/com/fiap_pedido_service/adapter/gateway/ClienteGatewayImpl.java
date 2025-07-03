package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.adapter.client.ClienteClient;
import com.fiap_pedido_service.core.domain.Cliente;
import com.fiap_pedido_service.core.gateway.ClienteGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteClient client;

    @Override
    public Cliente obtemDadosCliente(UUID idCliente) {

//        Cliente cliente = client.getCliente(idCliente);

        return new Cliente(
                UUID.randomUUID(),
                "Carol",
                "123.123.123-12",
                "Rua Apinajes 1268"
        );
    }
}
