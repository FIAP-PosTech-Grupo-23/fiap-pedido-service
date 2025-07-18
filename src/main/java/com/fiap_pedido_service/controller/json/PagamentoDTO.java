package com.fiap_pedido_service.controller.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagamentoDTO {
    private String numeroCartao;
    private String cvv;
    private String dataVencimento;
}
