package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.adapter.entity.PedidoEntity;
import com.fiap_pedido_service.adapter.entity.ProdutoPedidoEntity;
import com.fiap_pedido_service.adapter.repository.PedidoRepository;
import com.fiap_pedido_service.core.gateway.PedidoGateway;
import com.fiap_pedido_service.domain.pedido.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public void salvaPedido(Pedido pedido) {

        PedidoEntity pedidoEntity = new PedidoEntity(pedido.getIdCliente(),
                pedido.getPagamento().getId(),
                pedido.getStatusEnum(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        List<ProdutoPedidoEntity> produtosEntity = pedido.getProdutos().stream().map(p ->
                new ProdutoPedidoEntity(pedidoEntity, p.getSku(), p.getQuantidade())
        ).toList();

        pedidoEntity.setProdutos(produtosEntity);

        log.info("SALVANDO PEDIDO");

        pedidoRepository.save(pedidoEntity);

    }
}
