package com.fiap_pedido_service.core.gateway;

import com.fiap_pedido_service.domain.pedido.Pagamento;

import java.math.BigDecimal;

public interface PagamentoGateway {
    void solicitaPagamento(BigDecimal valorTotal, Pagamento pagamento, String nome, String cpf, String endereco);
}
