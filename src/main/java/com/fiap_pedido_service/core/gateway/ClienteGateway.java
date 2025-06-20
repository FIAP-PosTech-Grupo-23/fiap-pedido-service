package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.domain.Cliente;
import com.fiap_pedido_service.domain.Produto;

public interface ClienteGateway {
    Cliente obtemDadosCliente(Long idCliente);
}
