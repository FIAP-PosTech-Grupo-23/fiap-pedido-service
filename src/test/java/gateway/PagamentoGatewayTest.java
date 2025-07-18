package gateway;

import com.fiap_pedido_service.gateway.client.PagamentoClient;
import com.fiap_pedido_service.gateway.PagamentoGatewayImpl;
import com.fiap_pedido_service.controller.json.PagamentoRequestDTO;
import com.fiap_pedido_service.domain.Pagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PagamentoGatewayTest {
    @Mock
    private PagamentoClient pagamentoClient;

    @InjectMocks
    private PagamentoGatewayImpl pagamentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void solicitaPagamento_deveRetornarIdPagamento_quandoSucesso() {
        // Arrange
        BigDecimal valorTotal = BigDecimal.valueOf(100.00);
        Pagamento pagamento = new Pagamento("4111111111111111", "123", "12/30");
        String nome = "João da Silva";
        String cpf = "12345678900";
        String endereco = "Rua Exemplo, 123";

        Long idEsperado = 42L;

        when(pagamentoClient.processaPagamento(any(PagamentoRequestDTO.class))).thenReturn(idEsperado);

        // Act
        Long idRetornado = pagamentoGateway.solicitaPagamento(valorTotal, pagamento, nome, cpf, endereco);

        // Assert
        assertEquals(idEsperado, idRetornado);

        ArgumentCaptor<PagamentoRequestDTO> captor = ArgumentCaptor.forClass(PagamentoRequestDTO.class);
        verify(pagamentoClient, times(1)).processaPagamento(captor.capture());

        PagamentoRequestDTO dtoEnviado = captor.getValue();
        assertEquals(valorTotal, dtoEnviado.getValorTotal());
        assertEquals("4111111111111111", dtoEnviado.getPagamento().getNumeroCartao());
        assertEquals("123", dtoEnviado.getPagamento().getCvv());
        assertEquals("12/30", dtoEnviado.getPagamento().getDataVencimento());
        assertEquals(nome, dtoEnviado.getNome());
        assertEquals(cpf, dtoEnviado.getCpf());
        assertEquals(endereco, dtoEnviado.getEndereco());

    }
}
