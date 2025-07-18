package controller;

import com.fiap_pedido_service.adapter.PedidoListener;
import com.fiap_pedido_service.adapter.json.PagamentoDTO;
import com.fiap_pedido_service.adapter.json.PedidoDTO;
import com.fiap_pedido_service.adapter.json.ProdutoDTO;
import com.fiap_pedido_service.core.domain.Pedido;
import com.fiap_pedido_service.core.usecase.ProcessaPedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class PedidoListenerTest {


    @InjectMocks
    private PedidoListener pedidoListener;

    @Mock
    private ProcessaPedidoUseCase processaPedidoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeveEscutarTopicoEProcessarPedido() {

        // Arrange
        PedidoDTO pedidoDTO = new PedidoDTO(
                UUID.randomUUID(),
                List.of(new ProdutoDTO(
                        1L,
                        "123-abc",
                        "Celular",
                        "Celular amarelo",
                        BigDecimal.TEN,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        10
                )),
                new PagamentoDTO("123", "123", "05/2030")
        );

        // Act
        pedidoListener.processaPedido(pedidoDTO);

        // Assert
        verify(processaPedidoUseCase).processaPedido(any(Pedido.class));

    }
}
