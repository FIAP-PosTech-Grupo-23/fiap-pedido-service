package adapter.gateway;

import com.fiap_pedido_service.adapter.client.ClienteClient;
import com.fiap_pedido_service.adapter.exception.ClienteNotFoundException;
import com.fiap_pedido_service.adapter.gateway.ClienteGatewayImpl;
import com.fiap_pedido_service.adapter.json.ClienteDTO;
import com.fiap_pedido_service.core.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteGatewayTest {

    @Mock
    private ClienteClient clienteClient;

    @InjectMocks
    private ClienteGatewayImpl clienteGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarClienteQuandoClienteExistir() {

        UUID id = UUID.randomUUID();
        ClienteDTO dto = new ClienteDTO(id, "João", "12345678900", "1111111111", "2025-02-05", LocalDate.now(),"Rua A");
        when(clienteClient.getCliente(id)).thenReturn(dto);

        Cliente cliente = clienteGateway.obtemDadosCliente(id);

        assertNotNull(cliente);
        assertEquals(id, cliente.getId());
        assertEquals("João", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
        assertEquals("Rua A", cliente.getEndereco());

        verify(clienteClient, times(1)).getCliente(id);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {

        UUID id = UUID.randomUUID();
        when(clienteClient.getCliente(id)).thenThrow(new RuntimeException("Cliente não encontrado"));

        assertThrows(
                ClienteNotFoundException.class,
                () -> clienteGateway.obtemDadosCliente(id)
        );

        verify(clienteClient, times(1)).getCliente(id);
    }
}
