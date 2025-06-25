package com.fiap_pedido_service.adapter.client;

import com.fiap_pedido_service.adapter.json.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "produto-client", url = "${produto.api.url}")
public interface ProdutoClient {

    @PostMapping("/listar")
    List<ProdutoDTO> getProdutosPorSkus(@RequestBody List<String> skus);


}
