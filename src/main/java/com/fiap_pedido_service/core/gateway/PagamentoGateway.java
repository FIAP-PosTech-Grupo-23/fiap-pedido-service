package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.domain.pedido.TipoPagamento;

import java.math.BigDecimal;

public interface PagamentoGateway {
    void solicitaPagamento(BigDecimal valorTotal, TipoPagamento tipoPagamento, Long nome, Long cpf, Long endereco);
}
