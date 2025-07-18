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

    public Produto(Long id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Produto(String sku, int quantidade) {
        this.sku = sku;
        this.quantidade = quantidade;
    }

    public Produto(Long id, String sku, String nome, String descricao, BigDecimal preco, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public Produto(Long id, String sku, String nome, String descricao, BigDecimal preco, LocalDateTime criadoEm, LocalDateTime atualizadoEm, int quantidade) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
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

    protected BigDecimal calculaValorTotalProduto(){
        return this.preco.multiply(BigDecimal.valueOf(this.quantidade));
    }
}
