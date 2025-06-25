package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.core.domain.Pagamento;

import java.math.BigDecimal;

public interface PagamentoGateway {
    Long solicitaPagamento(BigDecimal valorTotal, Pagamento pagamento, String nome, String cpf, String endereco);
}
