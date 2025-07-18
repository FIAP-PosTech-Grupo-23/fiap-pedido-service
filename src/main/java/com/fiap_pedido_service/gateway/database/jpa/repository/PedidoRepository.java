package com.fiap_pedido_service.gateway.database.jpa.repository;

import com.fiap_pedido_service.gateway.database.jpa.entity.PedidoEntity;
import com.fiap_pedido_service.domain.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    PedidoEntity findByIdPagamento(Long idPagamento);

    @Transactional
    @Modifying
    @Query("update PedidoEntity p set p.status = ?1, p.atualizadoEm = ?2 where p.idPagamento = ?3")
    int updateStatusAndAtualizadoEmByIdPagamento(StatusEnum status, LocalDateTime atualizadoEm, Long idPagamento);
}
