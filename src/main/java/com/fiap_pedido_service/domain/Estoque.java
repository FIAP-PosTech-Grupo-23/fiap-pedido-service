package com.fiap_pedido_service.domain;

public class Estoque {

    private Long idProduto;
    private int quantidade;
    private EstoqueEnum estoqueEnum;

    public Estoque(Long idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Estoque(Long idProduto, int quantidade, EstoqueEnum estoqueEnum) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.estoqueEnum = estoqueEnum;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isEstoqueIndisponivel(){
        return estoqueEnum == EstoqueEnum.INDISPONIVEL;
    }

}
