package com.fiap_pedido_service.core.domain;

public class Estoque {
    private Long id;
    private Long idProduto;
    private int quantidade;
    private EstoqueEnum estoqueEnum;

    public Estoque(Long idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Estoque(Long id, Long idProduto, int quantidade, EstoqueEnum estoqueEnum) {
        this.id = id;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.estoqueEnum = estoqueEnum;
    }

    public EstoqueEnum getEstoqueEnum() {
        return estoqueEnum;
    }
}
