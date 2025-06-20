package com.fiap_pedido_service.adapter.json;

import lombok.Data;

@Data
public class PedidoMessage {
    private String sku;
    private int quantidade;
    private Long idCliente;
    private CartaoCreditoJson cartaoCreditoJson;
    private PixJson pixJson;
}
