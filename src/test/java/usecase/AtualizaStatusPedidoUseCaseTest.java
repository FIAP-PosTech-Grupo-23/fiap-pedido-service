package usecase;

import com.fiap_pedido_service.domain.*;
import com.fiap_pedido_service.gateway.EstoqueGateway;
import com.fiap_pedido_service.gateway.PedidoGateway;
import com.fiap_pedido_service.usecase.AtualizaStatusPedidoUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AtualizaStatusPedidoUseCaseTest {

    @InjectMocks
    private AtualizaStatusPedidoUseCaseImpl atualizaStatusPedidoUseCase;

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private EstoqueGateway estoqueGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("✅ Deve atualizar status do pedido para FECHADO_COM_SUCESSO sem alterar estoque")
    void deveAtualizarStatusComSucessoSemAlterarEstoque() {
        // Arrange
        PedidoStatusPagamento statusPagamento = new PedidoStatusPagamento(123L, StatusPagamentoEnum.APROVADO);
        UUID uuidCliente = UUID.randomUUID();

        Produto produtoRequest = new Produto("sku123", 2);
        Pedido pedido = new Pedido(new Cliente(uuidCliente), List.of(produtoRequest), new Pagamento(1L), StatusEnum.ABERTO, null);

        when(pedidoGateway.recuperaPedidoPorIdPagamento(123L)).thenReturn(pedido);

        // Act
        atualizaStatusPedidoUseCase.atualizaStatusPedido(statusPagamento);

        // Assert
        verify(pedidoGateway).atualizaStatus(123L, StatusEnum.FECHADO_COM_SUCESSO);
        verify(estoqueGateway, never()).baixaEstoque(any());
    }

    @Test
    @DisplayName("❌ Deve atualizar status do pedido para FECHADO_SEM_ESTOQUE e estornar estoque")
    void deveAtualizarStatusComFalhaEEstornarEstoque() {
        // Arrange
        UUID uuidCliente = UUID.randomUUID();

        Produto produto1 = new Produto(1L, "sku1", "Produto 1", "Produto Teste", BigDecimal.valueOf(2), LocalDateTime.now(), LocalDateTime.now());

        Produto produto2 = new Produto(2L, "sku2", "Produto 2", "Produto Teste", BigDecimal.valueOf(3), LocalDateTime.now(), LocalDateTime.now());

        Pedido pedido = new Pedido(new Cliente(uuidCliente), List.of(produto1, produto2), new Pagamento("123", "123", "05/2030"), StatusEnum.ABERTO, null);

        PedidoStatusPagamento statusPagamento = new PedidoStatusPagamento(456L, StatusPagamentoEnum.REPROVADO);

        when(pedidoGateway.recuperaPedidoPorIdPagamento(456L)).thenReturn(pedido);

        // Act
        atualizaStatusPedidoUseCase.atualizaStatusPedido(statusPagamento);

        // Assert
        verify(pedidoGateway).atualizaStatus(456L, StatusEnum.FECHADO_SEM_CREDITO);

    }

}
