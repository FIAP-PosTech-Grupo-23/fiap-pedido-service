package com.fiap_pedido_service.core.domain;

public enum StatusPagamentoEnum {

    APROVADO(StatusEnum.FECHADO_COM_SUCESSO),
    REPROVADO(StatusEnum.FECHADO_SEM_CREDITO);

    private final StatusEnum statusInterno;

    StatusPagamentoEnum(StatusEnum statusInterno) {
        this.statusInterno = statusInterno;
    }

    public StatusEnum getStatusInterno() {
        return statusInterno;
    }

}
