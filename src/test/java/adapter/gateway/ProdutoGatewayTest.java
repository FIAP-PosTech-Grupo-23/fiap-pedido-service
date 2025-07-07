package adapter.gateway;

import com.fiap_pedido_service.adapter.client.ProdutoClient;
import com.fiap_pedido_service.adapter.gateway.ProdutoGatewayImpl;
import com.fiap_pedido_service.adapter.json.ProdutoDTO;
import com.fiap_pedido_service.core.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProdutoGatewayTest {

    @Mock
    private ProdutoClient produtoClient;

    @InjectMocks
    private ProdutoGatewayImpl produtoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtemDadosProdutos_deveRetornarListaDeProdutos_quandoClientRetornaDados() {
        // Arrange
        List<String> skus = List.of("sku1", "sku2");
        ProdutoDTO dto1 = new ProdutoDTO(1L, "sku1", "Produto 1", "Descrição 1", BigDecimal.TEN, LocalDateTime.now(), LocalDateTime.now(), 10);
        ProdutoDTO dto2 = new ProdutoDTO(2L, "sku2", "Produto 2", "Descrição 2", BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), 20);

        when(produtoClient.getProdutosPorSkus(skus)).thenReturn(List.of(dto1, dto2));

        // Act
        List<Produto> produtos = produtoGateway.obtemDadosProdutos(skus);

        // Assert
        assertEquals(2, produtos.size());

        Produto p1 = produtos.get(0);
        assertEquals("sku1", p1.getSku());
        assertEquals("Produto 1", p1.getNome());

        Produto p2 = produtos.get(1);
        assertEquals("sku2", p2.getSku());
        assertEquals("Produto 2", p2.getNome());
    }

    @Test
    void obtemDadosProdutos_deveRetornarListaVazia_quandoClientLancaExcecao() {
        // Arrange
        List<String> skus = List.of("sku1", "sku2");
        when(produtoClient.getProdutosPorSkus(skus)).thenThrow(new RuntimeException("Erro ao chamar produto"));

        // Act
        List<Produto> produtos = produtoGateway.obtemDadosProdutos(skus);

        // Assert
        assertNotNull(produtos);
        assertTrue(produtos.isEmpty());
    }

}
