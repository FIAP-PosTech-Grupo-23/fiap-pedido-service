package com.fiap_pedido_service.core.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private Long id;
    private UUID idCliente;
    private List<Produto> produtos;
    private Pagamento pagamento;
    private StatusEnum statusEnum;
    private BigDecimal valorTotal;

    public Pedido(Long id, UUID idCliente, List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum, BigDecimal valorTotal) {
        this.id = id;
        this.idCliente = idCliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
        this.valorTotal = valorTotal;
    }

    public Pedido(UUID idCliente, List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum) {
        this.idCliente = idCliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
    }

    public Pedido(UUID idCliente, List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum, BigDecimal valorTotal) {
        this.idCliente = idCliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
        this.valorTotal = valorTotal;
    }

    public Pedido(UUID idCliente, List<Produto> produtos, StatusEnum statusEnum, BigDecimal valorTotal) {
        this.idCliente = idCliente;
        this.produtos = produtos;
        this.statusEnum = statusEnum;
        this.valorTotal = valorTotal;
    }

    public Pedido(UUID idCliente, StatusEnum statusEnum) {
        this.idCliente = idCliente;
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal calculaValorTotal() {
        return produtos.stream()
                .map(p ->
                        p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
