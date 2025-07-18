package com.fiap_pedido_service.domain;

public class PedidoStatusPagamento {

    private Long idPagamento;

    private StatusPagamentoEnum statusPagamento;

    public PedidoStatusPagamento(Long idPagamento, StatusPagamentoEnum statusPagamento) {
        this.idPagamento = idPagamento;
        this.statusPagamento = statusPagamento;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public StatusPagamentoEnum getStatusPagamento() {
        return statusPagamento;
    }
}
