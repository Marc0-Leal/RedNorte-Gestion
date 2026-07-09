package RedNorte.Gestion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import RedNorte.Gestion.model.Comuna;
import RedNorte.Gestion.repository.ComunaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComunaServiceTest {

    @Mock
    private ComunaRepository comunaRepository;

    @InjectMocks
    private ComunaService comunaService;

    private Comuna comuna;

    @BeforeEach
    void setUp() {
        comuna = new Comuna();
        comuna.setId(1L);
        comuna.setNombre("Santiago");
    }

    @Test
    void findAll_debeRetornarListaDeComunas() {
        List<Comuna> lista = Arrays.asList(comuna, new Comuna());
        when(comunaRepository.findAll()).thenReturn(lista);

        List<Comuna> resultado = comunaService.findAll();

        assertEquals(2, resultado.size());
        verify(comunaRepository, times(1)).findAll();
    }

    @Test
    void findById_conIdExistente_debeRetornarComuna() {
        when(comunaRepository.findById(1L)).thenReturn(Optional.of(comuna));

        Comuna resultado = comunaService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Santiago", resultado.getNombre());
        verify(comunaRepository, times(1)).findById(1L);
    }

    @Test
    void findById_conIdInexistente_debeRetornarNull() {
        when(comunaRepository.findById(99L)).thenReturn(Optional.empty());

        Comuna resultado = comunaService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarComuna() {
        when(comunaRepository.save(comuna)).thenReturn(comuna);

        Comuna resultado = comunaService.save(comuna);

        assertNotNull(resultado);
        assertEquals("Santiago", resultado.getNombre());
        verify(comunaRepository, times(1)).save(comuna);
    }

    @Test
    void patchComuna_conIdExistente_debeActualizarNombre() {
        Comuna actualizacion = new Comuna();
        actualizacion.setId(1L);
        actualizacion.setNombre("Providencia");

        when(comunaRepository.findById(1L)).thenReturn(Optional.of(comuna));
        when(comunaRepository.save(any(Comuna.class))).thenReturn(comuna);

        Comuna resultado = comunaService.patchComuna(actualizacion);

        assertNotNull(resultado);
        verify(comunaRepository, times(1)).save(any(Comuna.class));
    }

    @Test
    void patchComuna_conIdInexistente_debeRetornarNull() {
        Comuna actualizacion = new Comuna();
        actualizacion.setId(99L);

        when(comunaRepository.findById(99L)).thenReturn(Optional.empty());

        Comuna resultado = comunaService.patchComuna(actualizacion);

        assertNull(resultado);
        verify(comunaRepository, never()).save(any());
    }

    @Test
    void patchComuna_conNombreNulo_noDebeActualizarNombre() {
        Comuna actualizacion = new Comuna();
        actualizacion.setId(1L);
        actualizacion.setNombre(null);

        when(comunaRepository.findById(1L)).thenReturn(Optional.of(comuna));
        when(comunaRepository.save(any(Comuna.class))).thenReturn(comuna);

        comunaService.patchComuna(actualizacion);

        assertEquals("Santiago", comuna.getNombre());
    }

    @Test
    void deleteById_debeEliminarComuna() {
        doNothing().when(comunaRepository).deleteById(1L);

        comunaService.deleteById(1L);

        verify(comunaRepository, times(1)).deleteById(1L);
    }
}