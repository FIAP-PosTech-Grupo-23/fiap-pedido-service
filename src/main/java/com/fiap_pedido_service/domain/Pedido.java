package com.fiap_pedido_service.domain;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {

    private Long id;
    private Cliente cliente;
    private List<Produto> produtos;
    private Pagamento pagamento;
    private StatusEnum statusEnum;
    private BigDecimal valorTotal;

    public Pedido(Cliente cliente, List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
    }

    public Pedido(Pagamento pagamento, StatusEnum statusEnum) {
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
    }

    public Pedido(List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum) {
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
        this.valorTotal = calculaValorTotalPedido();
    }

    public Pedido(Cliente cliente, List<Produto> produtos, StatusEnum statusEnum, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.statusEnum = statusEnum;
        this.valorTotal = valorTotal;
    }

    public Pedido(Cliente cliente, List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
        this.valorTotal = valorTotal;
    }

    public Pedido(Long id, Cliente cliente, List<Produto> produtos, Pagamento pagamento, StatusEnum statusEnum, BigDecimal valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.pagamento = pagamento;
        this.statusEnum = statusEnum;
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
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

    private BigDecimal calculaValorTotalPedido() {
        return produtos.stream()
                .map(Produto::calculaValorTotalProduto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
