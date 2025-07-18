package com.fiap_pedido_service.controller.json;

import com.fiap_pedido_service.domain.StatusPagamentoEnum;
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
