package com.fiap_pedido_service.adapter.mapper;

import com.fiap_pedido_service.adapter.json.CartaoCreditoDTO;
import com.fiap_pedido_service.adapter.json.PagamentoDTO;
import com.fiap_pedido_service.adapter.json.PixDTO;
import com.fiap_pedido_service.domain.pedido.CartaoCredito;
import com.fiap_pedido_service.domain.pedido.Pagamento;
import com.fiap_pedido_service.domain.pedido.Pix;
import com.fiap_pedido_service.domain.pedido.TipoPagamentoEnum;

public class PagamentoMapper {

    public static Pagamento toDomain(PagamentoDTO dto) {
        if (dto instanceof CartaoCreditoDTO) {
            CartaoCreditoDTO ccDto = (CartaoCreditoDTO) dto;
            return new CartaoCredito(
                    TipoPagamentoEnum.CARTAO_CREDITO,
                    ccDto.getNumeroCartao(),
                    ccDto.getCvv(),
                    ccDto.getDataVencimento()
            );
        } else if (dto instanceof PixDTO) {
            PixDTO pixDto = (PixDTO) dto;
            return new Pix(
                    TipoPagamentoEnum.PIX,
                    pixDto.getChavePix());
        }
        throw new IllegalArgumentException("Tipo de pagamento não suportado: " + dto.getClass());
    }

}
