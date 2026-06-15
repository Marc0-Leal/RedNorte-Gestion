package RedNorte.Gestion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import RedNorte.Gestion.model.ListaEspera;
import RedNorte.Gestion.repository.ListaEsperaRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListaEsperaServiceTest {

    @Mock
    private ListaEsperaRepository listaEsperaRepository;

    @InjectMocks
    private ListaEsperaService listaEsperaService;

    private ListaEspera listaEspera;

    @BeforeEach
    void setUp() {
        listaEspera = new ListaEspera();
        listaEspera.setId(1L);
        listaEspera.setFecha_solitud(LocalDate.of(2024, 1, 10));
        listaEspera.setPrioridad("Alta");
    }

    @Test
    void findAll_debeRetornarListaDeListasEspera() {
        List<ListaEspera> lista = Arrays.asList(listaEspera, new ListaEspera());
        when(listaEsperaRepository.findAll()).thenReturn(lista);

        List<ListaEspera> resultado = listaEsperaService.findAll();

        assertEquals(2, resultado.size());
        verify(listaEsperaRepository, times(1)).findAll();
    }

    @Test
    void findById_conIdExistente_debeRetornarListaEspera() {
        when(listaEsperaRepository.findById(1L)).thenReturn(Optional.of(listaEspera));

        ListaEspera resultado = listaEsperaService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Alta", resultado.getPrioridad());
        verify(listaEsperaRepository, times(1)).findById(1L);
    }

    @Test
    void findById_conIdInexistente_debeRetornarNull() {
        when(listaEsperaRepository.findById(99L)).thenReturn(Optional.empty());

        ListaEspera resultado = listaEsperaService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarListaEspera() {
        when(listaEsperaRepository.save(listaEspera)).thenReturn(listaEspera);

        ListaEspera resultado = listaEsperaService.save(listaEspera);

        assertNotNull(resultado);
        assertEquals("Alta", resultado.getPrioridad());
        verify(listaEsperaRepository, times(1)).save(listaEspera);
    }

    @Test
    void patchListaEspera_conIdExistente_debeActualizarCampos() {
        ListaEspera actualizacion = new ListaEspera();
        actualizacion.setId(1L);
        actualizacion.setFecha_solitud(LocalDate.of(2024, 3, 1));
        actualizacion.setPrioridad("Media");

        when(listaEsperaRepository.findById(1L)).thenReturn(Optional.of(listaEspera));
        when(listaEsperaRepository.save(any(ListaEspera.class))).thenReturn(listaEspera);

        ListaEspera resultado = listaEsperaService.patchListaEspera(actualizacion);

        assertNotNull(resultado);
        verify(listaEsperaRepository, times(1)).save(any(ListaEspera.class));
    }

    @Test
    void patchListaEspera_conIdInexistente_debeRetornarNull() {
        ListaEspera actualizacion = new ListaEspera();
        actualizacion.setId(99L);

        when(listaEsperaRepository.findById(99L)).thenReturn(Optional.empty());

        ListaEspera resultado = listaEsperaService.patchListaEspera(actualizacion);

        assertNull(resultado);
        verify(listaEsperaRepository, never()).save(any());
    }

    @Test
    void deleteById_debeEliminarListaEspera() {
        doNothing().when(listaEsperaRepository).deleteById(1L);

        listaEsperaService.deleteById(1L);

        verify(listaEsperaRepository, times(1)).deleteById(1L);
    }
}