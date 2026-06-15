package RedNorte.Gestion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import RedNorte.Gestion.model.Pago;
import RedNorte.Gestion.repository.PagoRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    private Pago pago;

    @BeforeEach
    void setUp() {
        pago = new Pago();
        pago.setId(1L);
        pago.setMonto(50000);
        pago.setFecha_pago(LocalDate.of(2024, 1, 20));
        pago.setMetodo_pago("Tarjeta");
        pago.setEstado("Pagado");
    }

    @Test
    void findAll_debeRetornarListaDePagos() {
        List<Pago> lista = Arrays.asList(pago, new Pago());
        when(pagoRepository.findAll()).thenReturn(lista);

        List<Pago> resultado = pagoService.findAll();

        assertEquals(2, resultado.size());
        verify(pagoRepository, times(1)).findAll();
    }

    @Test
    void findById_conIdExistente_debeRetornarPago() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));

        Pago resultado = pagoService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Tarjeta", resultado.getMetodo_pago());
        verify(pagoRepository, times(1)).findById(1L);
    }

    @Test
    void findById_conIdInexistente_debeRetornarNull() {
        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());

        Pago resultado = pagoService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarPago() {
        when(pagoRepository.save(pago)).thenReturn(pago);

        Pago resultado = pagoService.save(pago);

        assertNotNull(resultado);
        assertEquals(50000, resultado.getMonto());
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    void patchPago_conIdExistente_debeActualizarCampos() {
        Pago actualizacion = new Pago();
        actualizacion.setId(1L);
        actualizacion.setMonto(75000);
        actualizacion.setFecha_pago(LocalDate.of(2024, 2, 15));
        actualizacion.setMetodo_pago("Efectivo");
        actualizacion.setEstado("Pendiente");

        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        Pago resultado = pagoService.patchPago(actualizacion);

        assertNotNull(resultado);
        verify(pagoRepository, times(1)).save(any(Pago.class));
    }

    @Test
    void patchPago_conIdInexistente_debeRetornarNull() {
        Pago actualizacion = new Pago();
        actualizacion.setId(99L);

        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());

        Pago resultado = pagoService.patchPago(actualizacion);

        assertNull(resultado);
        verify(pagoRepository, never()).save(any());
    }

    @Test
    void deleteById_debeEliminarPago() {
        doNothing().when(pagoRepository).deleteById(1L);

        pagoService.deleteById(1L);

        verify(pagoRepository, times(1)).deleteById(1L);
    }
}