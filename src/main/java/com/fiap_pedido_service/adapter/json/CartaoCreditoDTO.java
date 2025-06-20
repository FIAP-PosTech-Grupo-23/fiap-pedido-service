package com.fiap_pedido_service.adapter.json;

import lombok.Data;

@Data
public class CartaoCreditoDTO extends PagamentoDTO {
    private String numeroCartao;
    private String cvv;
    private String dataVencimento;
}
