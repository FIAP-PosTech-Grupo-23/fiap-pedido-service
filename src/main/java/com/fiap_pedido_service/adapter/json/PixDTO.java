package com.fiap_pedido_service.adapter.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PixDTO extends PagamentoDTO {
    private String chavePix;
}
