package com.fiap_pedido_service.adapter.client;

import com.fiap_pedido_service.adapter.json.EstoqueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "estoque-client", url = "${estoque.api.url}")
public interface EstoqueClient {

    @PostMapping("/baixa")
    List<EstoqueDTO> baixaEstoque(@RequestBody List<EstoqueDTO> estoques);

}
