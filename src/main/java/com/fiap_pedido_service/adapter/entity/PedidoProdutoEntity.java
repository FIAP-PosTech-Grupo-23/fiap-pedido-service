package com.fiap_pedido_service.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    private String skuProduto;

    private Integer quantidade;

    public PedidoProdutoEntity(PedidoEntity pedido, String skuProduto, Integer quantidade) {
        this.pedido = pedido;
        this.skuProduto = skuProduto;
        this.quantidade = quantidade;
    }
}
