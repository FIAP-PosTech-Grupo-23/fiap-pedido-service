package com.fiap_pedido_service.domain.pedido;

import com.fiap_pedido_service.domain.StatusEnum;

public class Pedido {

    private Long id;
    private String sku;
    private int quantidade;
    private Long idCliente;
    private CartaoCredito cartaoCredito;
    private Pix pix;
    private StatusEnum statusEnum;

    public Pedido(String sku, int quantidade, Long idCliente, String numeroCartaoCredito, String cvv, String dataVencimento) {
        this.sku = sku;
        this.quantidade = quantidade;
        this.idCliente = idCliente;
        this.cartaoCredito = new CartaoCredito(numeroCartaoCredito, cvv, dataVencimento);
    }

    public Pedido(String sku, int quantidade, Long idCliente, String codigoPix) {
        this.sku = sku;
        this.quantidade = quantidade;
        this.idCliente = idCliente;
        this.pix = new Pix(codigoPix);
    }

    public void abrePedido() {
        statusEnum = StatusEnum.ABERTO;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public Pix getPix() {
        return pix;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }
}
