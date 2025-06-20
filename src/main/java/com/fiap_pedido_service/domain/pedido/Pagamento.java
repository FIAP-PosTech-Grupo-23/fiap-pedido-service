package com.fiap_pedido_service.domain.pedido;

public abstract class Pagamento {
    private TipoPagamentoEnum tipoPagamento;

    public Pagamento(TipoPagamentoEnum tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
