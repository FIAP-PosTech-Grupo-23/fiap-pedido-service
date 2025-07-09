package core;

import com.fiap_pedido_service.core.domain.*;
import com.fiap_pedido_service.core.gateway.*;
import com.fiap_pedido_service.core.usecase.ProcessaPedidoUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ProcessaPedidoUseCaseTest {

    @InjectMocks
    private ProcessaPedidoUseCaseImpl processaPedidoUseCase;

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private EstoqueGateway estoqueGateway;

    @Mock
    private PagamentoGateway pagamentoGateway;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveProcessarPedidoComEstoqueDisponivel() {
        // Arrange
        UUID uuidCliente = UUID.randomUUID();

        Produto produtoRequest = new Produto("sku123", 2);
        Pedido pedido = new Pedido(uuidCliente, List.of(produtoRequest), new Pagamento(1L), StatusEnum.ABERTO, null);

        Produto produtoBanco = new Produto(1L, "sku123", "Produto Teste", "Produto Teste", BigDecimal.TEN, LocalDateTime.now(), LocalDateTime.now());

        Cliente cliente = new Cliente(uuidCliente, "Diego", "12345678900", "Rua Exemplo");

        Estoque estoque = new Estoque(1L, 1L, 2, EstoqueEnum.DISPONIVEL);

        when(produtoGateway.obtemDadosProdutos(List.of("sku123"))).thenReturn(List.of(produtoBanco));
        when(clienteGateway.obtemDadosCliente(uuidCliente)).thenReturn(cliente);
        when(estoqueGateway.baixaEstoque(anyList())).thenReturn(List.of(estoque));
        when(pagamentoGateway.solicitaPagamento(eq(BigDecimal.valueOf(20)), any(), eq("Diego"), eq("12345678900"), eq("Rua Exemplo")))
                .thenReturn(123L);

        // Act
        processaPedidoUseCase.processaPedido(pedido);

        // Assert
        ArgumentCaptor<Pedido> captor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidoGateway).salvaPedido(captor.capture());

        Pedido pedidoSalvo = captor.getValue();
        assertEquals(uuidCliente, pedidoSalvo.getIdCliente());
        assertEquals(BigDecimal.valueOf(20), pedidoSalvo.getValorTotal());
        assertEquals(123L, pedidoSalvo.getPagamento().getId());
    }

    @Test
    void deveProcessarPedidoComEstoqueIndisponivel() {
        // Arrange
        UUID uuidCliente = UUID.randomUUID();

        Produto produtoRequest = new Produto("sku123", 2);
        Pedido pedido = new Pedido(uuidCliente, List.of(produtoRequest), new Pagamento("123", "123", "05/2030"), StatusEnum.ABERTO, null);

        Produto produtoBanco = new Produto(1L, "sku123", "Produto Teste", "Produto Teste", BigDecimal.TEN, LocalDateTime.now(), LocalDateTime.now());

        Estoque estoque = new Estoque(1L, 1L, 1, EstoqueEnum.INDISPONIVEL);

        when(produtoGateway.obtemDadosProdutos(List.of("sku123"))).thenReturn(List.of(produtoBanco));
        when(clienteGateway.obtemDadosCliente(uuidCliente)).thenReturn(new Cliente(uuidCliente, "Diego", "123.123.123-12", "Rua FIAP"));
        when(estoqueGateway.baixaEstoque(anyList())).thenReturn(List.of(estoque));

        // Act
        processaPedidoUseCase.processaPedido(pedido);

        // Assert
        ArgumentCaptor<Pedido> captor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidoGateway).salvaPedido(captor.capture());

        Pedido pedidoSalvo = captor.getValue();
        assertEquals(StatusEnum.FECHADO_SEM_ESTOQUE, pedidoSalvo.getStatusEnum());
        assertEquals(BigDecimal.valueOf(20), pedidoSalvo.getValorTotal());
        verify(pagamentoGateway, never()).solicitaPagamento(any(), any(), any(), any(), any());
    }

    @Test
    void deveCalcularValorTotalCorretamente() {
        // Arrange
        UUID uuidCliente = UUID.randomUUID();

        Produto produto1 = new Produto("sku1", 1);
        Produto produto2 = new Produto("sku2", 3);

        // método protegido - testamos indiretamente via pedido completo
        Pedido pedido = new Pedido(uuidCliente, List.of(produto1, produto2), new Pagamento("123", "123", "05/2030"), StatusEnum.ABERTO, null);

        Produto p1Banco = new Produto(1L, "sku1", "Produto 1", "Produto Teste", BigDecimal.valueOf(5), LocalDateTime.now(), LocalDateTime.now());

        Produto p2Banco = new Produto(2L, "sku2", "Produto 2", "Produto Teste", BigDecimal.valueOf(7), LocalDateTime.now(), LocalDateTime.now());

        Cliente cliente = new Cliente(uuidCliente, "Maria", "98765432100", "Rua XYZ");

        Estoque estoque1 = new Estoque(1L, 1L, 1, EstoqueEnum.DISPONIVEL);

        Estoque estoque2 = new Estoque(2L, 2L, 3, EstoqueEnum.DISPONIVEL);

        when(produtoGateway.obtemDadosProdutos(List.of("sku1", "sku2"))).thenReturn(List.of(p1Banco, p2Banco));
        when(clienteGateway.obtemDadosCliente(uuidCliente)).thenReturn(cliente);
        when(estoqueGateway.baixaEstoque(anyList())).thenReturn(List.of(estoque1, estoque2));
        when(pagamentoGateway.solicitaPagamento(eq(BigDecimal.valueOf(26)), any(), any(), any(), any())).thenReturn(456L);

        // Act
        processaPedidoUseCase.processaPedido(pedido);

        // Assert
        ArgumentCaptor<Pedido> captor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidoGateway).salvaPedido(captor.capture());

        Pedido pedidoSalvo = captor.getValue();
        assertEquals(BigDecimal.valueOf(26), pedidoSalvo.getValorTotal());
    }

}
