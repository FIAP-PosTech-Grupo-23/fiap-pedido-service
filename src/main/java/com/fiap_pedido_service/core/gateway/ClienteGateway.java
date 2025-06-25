package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.core.domain.Cliente;

import java.util.UUID;

public interface ClienteGateway {
    Cliente obtemDadosCliente(UUID idCliente);
}
