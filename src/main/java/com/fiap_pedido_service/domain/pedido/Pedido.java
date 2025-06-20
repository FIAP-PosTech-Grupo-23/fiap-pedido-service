package com.fiap_pedido_service.domain.pedido;

import com.fiap_pedido_service.domain.Produto;
import com.fiap_pedido_service.domain.StatusEnum;

import java.util.List;

public class Pedido {

    private Long id;
    private Long idCliente;
    private List<Produto> produtos;
    private Pagamento pagamento;
    private StatusEnum statusEnum;

    public Pedido(Long idCliente, List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum) {
        this.idCliente = idCliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }
}
