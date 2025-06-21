package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.core.gateway.PedidoGateway;
import com.fiap_pedido_service.domain.pedido.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PedidoGatewayImpl implements PedidoGateway {
    @Override
    public void salvaPedido(Pedido pedido) {

        log.info("SALVANDO PEDIDO");
    }
}
