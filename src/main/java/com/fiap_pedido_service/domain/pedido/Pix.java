package com.fiap_pedido_service.domain.pedido;

public class Pix extends Pagamento{
    private String codigoPix;

    public Pix(TipoPagamentoEnum tipoPagamento, String codigoPix) {
        super(tipoPagamento);
        this.codigoPix = codigoPix;
    }
}
