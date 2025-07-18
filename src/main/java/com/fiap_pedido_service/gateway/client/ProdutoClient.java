package com.fiap_pedido_service.gateway.client;

import com.fiap_pedido_service.controller.json.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(value = "produto-client", url = "${produto.api.url}")
public interface ProdutoClient {

    @PostMapping("/listar")
    List<ProdutoDTO> getProdutosPorSkus(@RequestBody Set<String> skus);


}
