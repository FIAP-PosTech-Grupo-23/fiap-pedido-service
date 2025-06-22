package com.fiap_pedido_service.adapter.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;

    private String sku;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    private int quantidade;
}
