package com.fiap_pedido_service.adapter;

import com.fiap_pedido_service.adapter.json.PedidoDTO;
import com.fiap_pedido_service.adapter.mapper.PagamentoMapper;
import com.fiap_pedido_service.core.usecase.ProcessaPedidoUseCase;
import com.fiap_pedido_service.domain.Produto;
import com.fiap_pedido_service.domain.StatusEnum;
import com.fiap_pedido_service.domain.pedido.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoListener {

    private final ProcessaPedidoUseCase processaPedidoUseCase;

    @RabbitListener(queues = RabbitMQConfig.PEDIDO_QUEUE)
    public void receivePedido(PedidoDTO pedido) {

        processaPedidoUseCase.processaPedido(mapCriaPedido(pedido));

    }

    private Pedido mapCriaPedido(PedidoDTO peditoDto) {

        return new Pedido(peditoDto.getIdCliente(),
                peditoDto.getProdutosJson().stream()
                        .map(p ->
                                new Produto(p.getSku(), p.getQuantidade())
                        ).toList(),
                PagamentoMapper.toDomain(peditoDto.getPagamentoDTO()),
                StatusEnum.ABERTO);


    }

}
