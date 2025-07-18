package com.fiap_pedido_service.domain;

public class Pagamento {

    private Long id;
    private String numeroCartao;
    private String cvv;
    private String dataVencimento;

    public Pagamento(String numeroCartao, String cvv, String dataVencimento) {
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
        this.dataVencimento = dataVencimento;
    }

    public Pagamento(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getCvv() {
        return cvv;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }
}
