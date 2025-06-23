package com.fiap_pedido_service.adapter.entity;

import com.fiap_pedido_service.domain.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

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
    private List<ProdutoPedidoEntity> produtos;

    private Long idPagamento;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    public PedidoEntity(UUID idCliente, Long idPagamento, StatusEnum status, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.idCliente = idCliente;
        this.idPagamento = idPagamento;
        this.status = status;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }
}
