package com.fiap_pedido_service.adapter;

import com.fiap_pedido_service.adapter.config.RabbitMQConfig;
import com.fiap_pedido_service.adapter.json.PedidoDTO;
import com.fiap_pedido_service.core.domain.*;
import com.fiap_pedido_service.core.usecase.ProcessaPedidoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoListener {

    private final ProcessaPedidoUseCase processaPedidoUseCase;

    @RabbitListener(queues = RabbitMQConfig.PEDIDO_QUEUE)
    public void processaPedido(PedidoDTO pedido) {
        log.info("Recebendo mensagem: {}", pedido);

        processaPedidoUseCase.processaPedido(mapCriaPedido(pedido));

    }

    private Pedido mapCriaPedido(PedidoDTO peditoDto) {

        return new Pedido(new Cliente(peditoDto.getIdCliente()),
                peditoDto.getProdutos().stream()
                        .map(p ->
                                new Produto(p.getSku(), p.getQuantidade())
                        ).toList(),
                new Pagamento(peditoDto.getPagamento().getNumeroCartao(), peditoDto.getPagamento().getCvv(), peditoDto.getPagamento().getDataVencimento()),
                StatusEnum.ABERTO);


    }

}
