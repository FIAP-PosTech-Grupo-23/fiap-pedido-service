package com.fiap_pedido_service.adapter.json;

import com.fiap_pedido_service.core.domain.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoStatusPagamentoDTO {

    private Long idPagamento;

    private StatusPagamentoEnum statusPagamento;

}
