package com.fiap_pedido_service.domain;

public class Estoque {
    private Long idProduto;
    private int quantidade;

    public Estoque(Long idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }
}
