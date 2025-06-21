package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.core.gateway.PagamentoGateway;
import com.fiap_pedido_service.domain.pedido.Pagamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PagamentoGatewayImpl implements PagamentoGateway {
    @Override
    public void solicitaPagamento(BigDecimal valorTotal, Pagamento pagamento, String nome, String cpf, String endereco) {

        log.info("VALOR TOTAL {}", valorTotal);
    }
}
