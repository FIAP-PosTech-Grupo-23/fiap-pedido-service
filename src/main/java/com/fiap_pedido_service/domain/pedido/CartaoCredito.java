package com.fiap_pedido_service.domain.pedido;

public class CartaoCredito extends TipoPagamento{
    private String numeroCartao;
    private String cvv;
    private String dataVencimento;

    public CartaoCredito(String numeroCartao, String cvv, String dataVencimento) {
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
        this.dataVencimento = dataVencimento;
    }
}
