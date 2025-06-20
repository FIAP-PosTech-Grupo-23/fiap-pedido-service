package com.fiap_pedido_service.adapter.json;

import com.fiap_pedido_service.domain.pedido.TipoPagamentoEnum;
import lombok.Data;

@Data
public abstract class PagamentoDTO {
    private TipoPagamentoEnum tipoPagamento;
}
