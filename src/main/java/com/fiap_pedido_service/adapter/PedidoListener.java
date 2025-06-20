package com.fiap_pedido_service.adapter;

import com.fiap_pedido_service.adapter.json.PedidoMessage;
import com.fiap_pedido_service.core.usecase.ProcessaPedidoUseCase;
import com.fiap_pedido_service.domain.pedido.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PedidoListener {

    private final ProcessaPedidoUseCase processaPedidoUseCase;

    @RabbitListener(queues = RabbitMQConfig.PEDIDO_QUEUE)
    public void receivePedido(PedidoMessage pedido) {

        processaPedidoUseCase.processaPedido(mapCriaPedido(pedido));

    }

    private Pedido mapCriaPedido(PedidoMessage pedido) {

        if(Objects.nonNull(pedido.getCartaoCreditoJson())){
            return new Pedido(pedido.getSku(),
                    pedido.getQuantidade(),
                    pedido.getIdCliente(),
                    pedido.getCartaoCreditoJson().getNumeroCartao(),
                    pedido.getCartaoCreditoJson().getCvv(),
                    pedido.getCartaoCreditoJson().getDataVencimento());

        }else{
            return new Pedido(pedido.getSku(), pedido.getQuantidade(), pedido.getIdCliente(), pedido.getPixJson().getCodigo());

        }


    }

}
