package controller;

import com.fiap_pedido_service.controller.PedidoController;
import com.fiap_pedido_service.controller.json.PedidoStatusPagamentoDTO;
import com.fiap_pedido_service.domain.PedidoStatusPagamento;
import com.fiap_pedido_service.domain.StatusPagamentoEnum;
import com.fiap_pedido_service.usecase.AtualizaStatusPedidoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class PedidoControllerTest {


    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private AtualizaStatusPedidoUseCase atualizaStatusPedidoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarStatusPedidoPagamento() {

        // Arrange
        PedidoStatusPagamentoDTO pedidoStatusPagamentoDTO = new PedidoStatusPagamentoDTO(1L, StatusPagamentoEnum.APROVADO);

        // Act
        pedidoController.atualizaStatusPedidoPagamento(pedidoStatusPagamentoDTO);

        // Assert
        verify(atualizaStatusPedidoUseCase).atualizaStatusPedido(any(PedidoStatusPagamento.class));

    }
}
