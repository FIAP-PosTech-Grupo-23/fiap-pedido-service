package com.fiap_pedido_service.adapter.json;


import com.fiap_pedido_service.core.domain.EstoqueEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {

    private Long idProduto;
    private int quantidade;
    private EstoqueEnum status;

    public EstoqueDTO(Long idProduto, int quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }
}
