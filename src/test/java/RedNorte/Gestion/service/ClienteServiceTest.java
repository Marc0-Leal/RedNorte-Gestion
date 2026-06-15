package RedNorte.Gestion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import RedNorte.Gestion.model.Cliente;
import RedNorte.Gestion.repository.ClienteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setRut("12345678-9");
        cliente.setTelefono(912345678);
        cliente.setCorreo("juan@email.com");
        cliente.setDireccion("Calle 123");
    }

    @Test
    void findAll_debeRetornarListaDeClientes() {
        List<Cliente> lista = Arrays.asList(cliente, new Cliente());
        when(clienteRepository.findAll()).thenReturn(lista);

        List<Cliente> resultado = clienteService.findAll();

        assertEquals(2, resultado.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void findById_conIdExistente_debeRetornarCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void findById_conIdInexistente_debeRetornarNull() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        Cliente resultado = clienteService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.save(cliente);

        assertNotNull(resultado);
        assertEquals("12345678-9", resultado.getRut());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void patchCliente_conIdExistente_debeActualizarCampos() {
        Cliente actualizacion = new Cliente();
        actualizacion.setId(1L);
        actualizacion.setNombre("Carlos");
        actualizacion.setApellido("González");
        actualizacion.setRut("98765432-1");
        actualizacion.setTelefono(987654321);
        actualizacion.setCorreo("carlos@email.com");
        actualizacion.setDireccion("Av. Nueva 456");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.patchCliente(actualizacion);

        assertNotNull(resultado);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void patchCliente_conIdInexistente_debeRetornarNull() {
        Cliente actualizacion = new Cliente();
        actualizacion.setId(99L);

        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        Cliente resultado = clienteService.patchCliente(actualizacion);

        assertNull(resultado);
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void deleteById_debeEliminarCliente() {
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.deleteById(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}