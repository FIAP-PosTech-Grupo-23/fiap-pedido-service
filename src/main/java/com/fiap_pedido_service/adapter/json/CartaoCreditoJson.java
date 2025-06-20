package com.fiap_pedido_service.adapter.json;

import lombok.Data;

@Data
public class CartaoCreditoJson {
    private String numeroCartao;
    private String cvv;
    private String dataVencimento;
}
