package com.fiap_pedido_service.core.domain;

public class PedidoStatusPagamento {

    private Long idPagamento;

    private StatusPagamentoEnum statusPagamentoEnum;

    public PedidoStatusPagamento(Long idPagamento, StatusPagamentoEnum statusPagamentoEnum) {
        this.idPagamento = idPagamento;
        this.statusPagamentoEnum = statusPagamentoEnum;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public StatusPagamentoEnum getStatusPagamentoEnum() {
        return statusPagamentoEnum;
    }
}
