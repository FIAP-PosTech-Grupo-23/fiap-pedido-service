package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.adapter.client.ProdutoClient;
import com.fiap_pedido_service.adapter.json.ProdutoDTO;
import com.fiap_pedido_service.core.gateway.ProdutoGateway;
import com.fiap_pedido_service.core.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoClient client;

    @Override
    public List<Produto> obtemDadosProdutos(List<String> skus) {

        log.info("INICIANDO RECUPERACAO PRODUTOS");
        List<ProdutoDTO> produtosPorSkus = List.of();
        try{
            produtosPorSkus = client.getProdutosPorSkus(skus);
            log.info("Produtos Recuperados: {}", produtosPorSkus);
        }catch (Exception e){
            log.error("ERRO AO CHAMAR PRODUTO CLIENTE: {}", e.getMessage());
        }

        return produtosPorSkus.stream().map(p ->
                new Produto(
                        p.getId(),
                        p.getSku(),
                        p.getNome(),
                        p.getDescricao(),
                        p.getPreco(),
                        p.getCriadoEm(),
                        p.getAtualizadoEm()
                )
        ).toList();
    }
}
