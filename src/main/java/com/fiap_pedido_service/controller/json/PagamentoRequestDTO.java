package com.fiap_pedido_service.controller.json;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoRequestDTO {

    private BigDecimal valorTotal;
    private PagamentoDTO pagamento;
    private String nome;
    private String cpf;
    String endereco;



}
