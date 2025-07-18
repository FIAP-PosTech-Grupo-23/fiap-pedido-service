package com.fiap_pedido_service.gateway.database.jpa.entity;

import com.fiap_pedido_service.domain.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID idCliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProdutoEntity> pedidosProdutos;

    private Long idPagamento;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    private BigDecimal valorTotal;

    public PedidoEntity(UUID idCliente, Long idPagamento, StatusEnum status, LocalDateTime criadoEm, LocalDateTime atualizadoEm, BigDecimal valorTotal) {
        this.idCliente = idCliente;
        this.idPagamento = idPagamento;
        this.status = status;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.valorTotal = valorTotal;
    }
}
