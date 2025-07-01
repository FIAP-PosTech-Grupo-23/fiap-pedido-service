package com.fiap_pedido_service.adapter.client;

import com.fiap_pedido_service.adapter.json.PagamentoRequestDTO;
import com.fiap_pedido_service.core.domain.Estoque;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "pagamento-client", url = "${pagamento.api.url}")
public interface PagamentoClient {

    @PostMapping("/paga")
    Long paga(@RequestBody PagamentoRequestDTO pagamentoRequestDTO);


}
