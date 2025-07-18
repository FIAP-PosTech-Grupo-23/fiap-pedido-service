package com.fiap_pedido_service.gateway;

import com.fiap_pedido_service.domain.Pagamento;

import java.math.BigDecimal;

public interface PagamentoGateway {
    Long solicitaPagamento(BigDecimal valorTotal, Pagamento pagamento, String nome, String cpf, String endereco);
}
