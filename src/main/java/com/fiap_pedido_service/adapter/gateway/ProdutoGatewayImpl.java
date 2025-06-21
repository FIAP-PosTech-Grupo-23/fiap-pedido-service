package com.fiap_pedido_service.adapter.gateway;

import com.fiap_pedido_service.core.gateway.ProdutoGateway;
import com.fiap_pedido_service.domain.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoGatewayImpl implements ProdutoGateway {
    @Override
    public List<Produto> obtemDadosProdutos(List<String> sku) {

        Produto produto = new Produto(
                1L,
                "123",
                "Calça Jeans",
                "Calça jeans azul com cintura alta",
                BigDecimal.valueOf(200),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return List.of(produto);
    }
}
