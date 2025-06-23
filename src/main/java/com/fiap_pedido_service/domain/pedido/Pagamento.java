package com.fiap_pedido_service.domain.pedido;

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
}
