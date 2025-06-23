package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.core.gateway.ClienteGateway;
import com.fiap_pedido_service.domain.Cliente;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteGatewayImpl implements ClienteGateway {
    @Override
    public Cliente obtemDadosCliente(UUID idCliente) {
        return new Cliente(
                1L,
                "Carol",
                "123.123.123-12",
                "Rua Apinajes 1268"
        );
    }
}
