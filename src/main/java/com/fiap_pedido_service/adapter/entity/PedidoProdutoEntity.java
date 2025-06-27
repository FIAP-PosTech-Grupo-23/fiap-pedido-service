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
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedido;

    private Long idProduto;

    private Integer quantidade;

    public PedidoProdutoEntity(PedidoEntity pedido, Long idProduto, Integer quantidade) {
        this.pedido = pedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }
}
