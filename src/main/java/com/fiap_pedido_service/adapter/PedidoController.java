package com.fiap_pedido_service.adapter;

import com.fiap_pedido_service.adapter.json.PedidoStatusPagamentoDTO;
import com.fiap_pedido_service.core.domain.PedidoStatusPagamento;
import com.fiap_pedido_service.core.usecase.AtualizaStatusPedidoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final AtualizaStatusPedidoUseCase atualizaStatusPedidoUseCase;

    @PutMapping
    public ResponseEntity<Void> atualizaStatusPedidoPagamento(@RequestBody PedidoStatusPagamentoDTO pedidoStatusPagamento) {
        atualizaStatusPedidoUseCase.atualizaStatusPedido(criaProdutoDomain(pedidoStatusPagamento));
        return ResponseEntity.noContent().build();
    }

    private PedidoStatusPagamento criaProdutoDomain(PedidoStatusPagamentoDTO pedidoStatusPagamentoDTO) {
        return new PedidoStatusPagamento(pedidoStatusPagamentoDTO.getIdPagamento(), pedidoStatusPagamentoDTO.getStatusPagamento());

    }

}
