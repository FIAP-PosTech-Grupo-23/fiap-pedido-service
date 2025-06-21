package com.fiap_pedido_service.adapter.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fiap_pedido_service.domain.pedido.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipoPagamento"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartaoCreditoDTO.class, name = "CARTAO_CREDITO"), // Mapeia "CARTAO_CREDITO" para CartaoCreditoDTO
        @JsonSubTypes.Type(value = PixDTO.class, name = "PIX") // Mapeia "PIX" para PixDTO
})
public abstract class PagamentoDTO {
    private TipoPagamentoEnum tipoPagamento;
}
