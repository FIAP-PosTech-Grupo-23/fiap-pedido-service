package com.fiap_pedido_service.adapter.json;

import lombok.Data;

import java.util.List;

@Data
public class PedidoDTO {
    private Long idCliente;
    private List<ProdutoJson> produtosJson;
    private PagamentoDTO pagamentoDTO;

}
