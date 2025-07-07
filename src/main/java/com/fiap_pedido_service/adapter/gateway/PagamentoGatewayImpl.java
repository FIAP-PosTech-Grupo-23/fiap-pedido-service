package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.adapter.client.PagamentoClient;
import com.fiap_pedido_service.adapter.json.PagamentoDTO;
import com.fiap_pedido_service.adapter.json.PagamentoRequestDTO;
import com.fiap_pedido_service.core.gateway.PagamentoGateway;
import com.fiap_pedido_service.core.domain.Pagamento;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class PagamentoGatewayImpl implements PagamentoGateway {

    private final PagamentoClient pagamentoClient;

    @Override
    public Long solicitaPagamento(BigDecimal valorTotal, Pagamento pagamento, String nome, String cpf, String endereco) {

        PagamentoRequestDTO dto = new PagamentoRequestDTO(
                valorTotal,
                new PagamentoDTO(
                        pagamento.getNumeroCartao(),
                        pagamento.getCvv(), pagamento.getDataVencimento()),
                nome,
                cpf,
                endereco
        );

        return pagamentoClient.processaPagamento(dto);

    }
}
