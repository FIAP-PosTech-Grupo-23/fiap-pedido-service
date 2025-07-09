package adapter.gateway;

import com.fiap_pedido_service.adapter.entity.PedidoEntity;
import com.fiap_pedido_service.adapter.entity.PedidoProdutoEntity;
import com.fiap_pedido_service.adapter.gateway.PedidoGatewayImpl;
import com.fiap_pedido_service.adapter.repository.PedidoRepository;
import com.fiap_pedido_service.core.domain.Pagamento;
import com.fiap_pedido_service.core.domain.Pedido;
import com.fiap_pedido_service.core.domain.Produto;
import com.fiap_pedido_service.core.domain.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;

class PedidoGatewayTest {


    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoGatewayImpl pedidoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvaPedido_deveSalvarPedidoComProdutos() {
        Produto produto = new Produto(1L, 2);
        UUID uuidCliente = UUID.randomUUID();
        Pedido pedido = new Pedido(
                uuidCliente,
                List.of(produto),
                new Pagamento(999L),
                StatusEnum.ABERTO,
                BigDecimal.TEN
        );

        pedidoGateway.salvaPedido(pedido);

        ArgumentCaptor<PedidoEntity> captor = ArgumentCaptor.forClass(PedidoEntity.class);
        verify(pedidoRepository, times(1)).save(captor.capture());

        PedidoEntity savedEntity = captor.getValue();
        assertEquals(uuidCliente, savedEntity.getIdCliente());
        assertEquals(999L, savedEntity.getIdPagamento());
        assertEquals(StatusEnum.ABERTO, savedEntity.getStatus());
        assertEquals(BigDecimal.TEN, savedEntity.getValorTotal());
        assertEquals(1, savedEntity.getPedidosProdutos().size());

        PedidoProdutoEntity produtoSalvo = savedEntity.getPedidosProdutos().get(0);
        assertEquals(1L, produtoSalvo.getIdProduto());
        assertEquals(2, produtoSalvo.getQuantidade());
    }

    @Test
    void salvaPedido_deveSalvarPedidoSemProdutos() {
        Pedido pedido = new Pedido(
                UUID.randomUUID(),
                null,
                new Pagamento(999L),
                StatusEnum.ABERTO,
                BigDecimal.TEN
        );

        pedidoGateway.salvaPedido(pedido);

        verify(pedidoRepository, times(1)).save(any(PedidoEntity.class));
    }

    @Test
    void recuperaPedidoPorIdPagamento_deveRetornarPedido() {
        Long idPagamento = 999L;
        UUID uuidCliente = UUID.randomUUID();

        PedidoEntity entity = new PedidoEntity();
        entity.setId(1L);
        entity.setIdCliente(uuidCliente);
        entity.setIdPagamento(idPagamento);
        entity.setStatus(StatusEnum.ABERTO);
        entity.setValorTotal(BigDecimal.TEN);

        PedidoProdutoEntity produtoEntity = new PedidoProdutoEntity();
        produtoEntity.setIdProduto(1L);
        produtoEntity.setQuantidade(5);
        produtoEntity.setPedido(entity);

        entity.setPedidosProdutos(List.of(produtoEntity));

        when(pedidoRepository.findByIdPagamento(idPagamento)).thenReturn(entity);

        Pedido pedido = pedidoGateway.recuperaPedidoPorIdPagamento(idPagamento);

        assertEquals(uuidCliente, pedido.getIdCliente());
        assertEquals(1, pedido.getProdutos().size());
        assertEquals(1L, pedido.getProdutos().get(0).getId());
        assertEquals(5, pedido.getProdutos().get(0).getQuantidade());
        assertEquals(StatusEnum.ABERTO, pedido.getStatusEnum());
        assertEquals(BigDecimal.TEN, pedido.getValorTotal());
        assertEquals(999L, pedido.getPagamento().getId());
    }

    @Test
    void atualizaStatus_deveChamarUpdateStatusDoRepositorio() {
        Long idPagamento = 999L;
        StatusEnum status = StatusEnum.FECHADO_COM_SUCESSO;

        pedidoGateway.atualizaStatus(idPagamento, status);

        verify(pedidoRepository, times(1))
                .updateStatusAndAtualizadoEmByIdPagamento(eq(status), any(LocalDateTime.class), eq(idPagamento));
    }
}
