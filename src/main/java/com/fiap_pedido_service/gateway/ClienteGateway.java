package com.fiap_pedido_service.gateway;

import com.fiap_pedido_service.domain.Cliente;

import java.util.UUID;

public interface ClienteGateway {
    Cliente obtemDadosCliente(UUID idCliente);
}
