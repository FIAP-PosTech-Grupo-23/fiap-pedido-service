package com.fiap_pedido_service.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Produto {

    private Long id;

    private String sku;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    private int quantidade;

    public Produto(String sku, int quantidade) {
        this.sku = sku;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
