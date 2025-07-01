package com.fiap_pedido_service.adapter.client;

import com.fiap_pedido_service.core.domain.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "cliente-client", url = "${cliente.api.url}")
public interface ClienteClient {

    @GetMapping("/{idCliente}")
    Cliente getCliente(@PathVariable UUID idCliente);


}
