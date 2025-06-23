package com.fiap_pedido_service.adapter.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private UUID idCliente;
    private List<ProdutoDTO> produtos;
    private PagamentoDTO pagamento;

}
