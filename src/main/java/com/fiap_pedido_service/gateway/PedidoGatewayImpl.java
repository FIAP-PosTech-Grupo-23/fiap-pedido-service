package com.fiap_pedido_service.gateway;

import com.fiap_pedido_service.domain.*;
import com.fiap_pedido_service.gateway.database.jpa.entity.PedidoEntity;
import com.fiap_pedido_service.gateway.database.jpa.entity.PedidoProdutoEntity;
import com.fiap_pedido_service.gateway.database.jpa.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public void salvaPedido(Pedido pedido) {

        PedidoEntity pedidoEntity = new PedidoEntity(Objects.nonNull(pedido.getCliente()) ? pedido.getCliente().getId() : null,
                Objects.nonNull(pedido.getPagamento()) ? pedido.getPagamento().getId() : null,
                pedido.getStatusEnum(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                pedido.getValorTotal()
        );

        if(Objects.nonNull(pedido.getProdutos())){
            List<PedidoProdutoEntity> produtosEntity = pedido.getProdutos().stream()
                    .map(p ->
                            new PedidoProdutoEntity(pedidoEntity, p.getId(), p.getQuantidade())
                    ).toList();

            pedidoEntity.setPedidosProdutos(produtosEntity);

        }

        pedidoRepository.save(pedidoEntity);

    }

    @Override
    public Pedido recuperaPedidoPorIdPagamento(Long idPagamento) {

        PedidoEntity pedidoEntity = pedidoRepository.findByIdPagamento(idPagamento);

        List<PedidoProdutoEntity> pedidosProdutos = pedidoEntity.getPedidosProdutos();

        List<Produto> produtos = pedidosProdutos.stream().map(p ->
                new Produto(
                        p.getIdProduto(),
                        p.getQuantidade()
                )
        ).toList();

        return new Pedido(
                pedidoEntity.getId(),
                new Cliente(pedidoEntity.getIdCliente()),
                produtos,
                new Pagamento(pedidoEntity.getIdPagamento()),
                pedidoEntity.getStatus(),
                pedidoEntity.getValorTotal()

        );

    }

    @Override
    @Transactional
    public void atualizaStatus(Long idPagamento, StatusEnum status) {

        pedidoRepository.updateStatusAndAtualizadoEmByIdPagamento(status, LocalDateTime.now(), idPagamento);

    }
}
