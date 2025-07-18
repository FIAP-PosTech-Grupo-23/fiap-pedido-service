package com.fiap_pedido_service.domain;

import java.util.UUID;

public class Cliente {
    private UUID id;
    private String nome;
    private String cpf;
    private String endereco;

    public Cliente(UUID id) {
        this.id = id;
    }

    public Cliente(UUID id, String nome, String cpf, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }
}
