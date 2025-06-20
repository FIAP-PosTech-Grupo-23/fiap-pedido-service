package com.fiap_pedido_service.domain.pedido;

public class Pix extends TipoPagamento{
    private String codigoPix;

    public Pix(String codigoPix) {
        this.codigoPix = codigoPix;
    }
}
