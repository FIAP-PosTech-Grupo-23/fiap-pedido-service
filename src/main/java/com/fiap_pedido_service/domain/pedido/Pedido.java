package com.fiap_pedido_service.domain.pedido;

import com.fiap_pedido_service.domain.Produto;
import com.fiap_pedido_service.domain.StatusEnum;

import java.util.List;
import java.util.UUID;

public class Pedido {

    private Long id;
    private UUID idCliente;
    private List<Produto> produtos;
    private Pagamento pagamento;
    private StatusEnum statusEnum;

    public Pedido(UUID idCliente, List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum) {
        this.idCliente = idCliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }
}
