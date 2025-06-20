package com.fiap_pedido_service.domain.pedido;

public class CartaoCredito extends Pagamento{

    private String numeroCartao;
    private String cvv;
    private String dataVencimento;

    public CartaoCredito(TipoPagamentoEnum tipoPagamento, String numeroCartao, String cvv, String dataVencimento) {
        super(tipoPagamento);
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
        this.dataVencimento = dataVencimento;
    }
}
