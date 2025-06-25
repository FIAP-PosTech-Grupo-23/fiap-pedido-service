package com.fiap_pedido_service.core.usecase;

import com.fiap_pedido_service.core.domain.*;
import com.fiap_pedido_service.core.domain.Pedido;
import com.fiap_pedido_service.core.gateway.EstoqueGateway;
import com.fiap_pedido_service.core.gateway.PedidoGateway;
import com.fiap_pedido_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtualizaStatusPedidoUseCaseImpl implements AtualizaStatusPedidoUseCase{

    private final PedidoGateway pedidoGateway;
    private final EstoqueGateway estoqueGateway;
    private final ProdutoGateway produtoGateway;

    @Override
    public void atualizaStatusPedido(PedidoStatusPagamento pedidoStatusPagamento) {

        StatusPagamentoEnum statusPagamentoEnum = pedidoStatusPagamento.getStatusPagamento();
        StatusEnum status = statusPagamentoEnum.getStatusInterno();

        Pedido pedido = pedidoGateway.recuperaPedidoPorIdPagamento(pedidoStatusPagamento.getIdPagamento());

        if(status.equals(StatusEnum.FECHADO_COM_SUCESSO)){

            pedidoGateway.atualizaStatus(pedidoStatusPagamento.getIdPagamento(), status);

        }else{

            List<String> skus = pedido.getProdutos().stream()
                    .map(Produto::getSku).toList();

            Map<String, Integer> mapSkuPorQuantidade = pedido.getProdutos().stream()
                    .collect(Collectors.toMap(Produto::getSku, Produto::getQuantidade));

            //aqui tenho o id
            List<Produto> produtos = produtoGateway.obtemDadosProdutos(skus);

            List<Estoque> estoques = produtos.stream().map(p ->
                new Estoque(p.getId(), mapSkuPorQuantidade.get(p.getSku()) * -1)
            ).toList();

            pedidoGateway.atualizaStatus(pedidoStatusPagamento.getIdPagamento(), status);

            estoqueGateway.baixaEstoque(estoques);

        }

    }
}
