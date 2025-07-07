package com.fiap_pedido_service.adapter.client;

import com.fiap_pedido_service.adapter.json.PagamentoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "pagamento-client", url = "${pagamento.api.url}")
public interface PagamentoClient {

    @PostMapping()
    Long processaPagamento(@RequestBody PagamentoRequestDTO pagamentoRequestDTO);


}
