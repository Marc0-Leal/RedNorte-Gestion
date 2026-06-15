package RedNorte.Gestion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import RedNorte.Gestion.model.CitaMedica;
import RedNorte.Gestion.repository.CitaMedicaRepository;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitaMedicaServiceTest {

    @Mock
    private CitaMedicaRepository citaMedicaRepository;

    @InjectMocks
    private CitaMedicaService citaMedicaService;

    private CitaMedica citaMedica;

    @BeforeEach
    void setUp() {
        citaMedica = new CitaMedica();
        citaMedica.setId(1L);
        citaMedica.setFecha(Date.valueOf("2024-01-15"));
        citaMedica.setHora(10);
        citaMedica.setEstado("Pendiente");
    }

    @Test
    void findAll_debeRetornarListaDeCitasMedicas() {
        List<CitaMedica> lista = Arrays.asList(citaMedica, new CitaMedica());
        when(citaMedicaRepository.findAll()).thenReturn(lista);

        List<CitaMedica> resultado = citaMedicaService.findAll();

        assertEquals(2, resultado.size());
        verify(citaMedicaRepository, times(1)).findAll();
    }

    @Test
    void findById_conIdExistente_debeRetornarCitaMedica() {
        when(citaMedicaRepository.findById(1L)).thenReturn(Optional.of(citaMedica));

        CitaMedica resultado = citaMedicaService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(citaMedicaRepository, times(1)).findById(1L);
    }

    @Test
    void findById_conIdInexistente_debeRetornarNull() {
        when(citaMedicaRepository.findById(99L)).thenReturn(Optional.empty());

        CitaMedica resultado = citaMedicaService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarCitaMedica() {
        when(citaMedicaRepository.save(citaMedica)).thenReturn(citaMedica);

        CitaMedica resultado = citaMedicaService.save(citaMedica);

        assertNotNull(resultado);
        assertEquals("Pendiente", resultado.getEstado());
        verify(citaMedicaRepository, times(1)).save(citaMedica);
    }

    @Test
    void patchCitaMedica_conIdExistente_debeActualizarCampos() {
        CitaMedica actualizacion = new CitaMedica();
        actualizacion.setId(1L);
        actualizacion.setFecha(Date.valueOf("2024-02-20"));
        actualizacion.setHora(14);
        actualizacion.setEstado("Confirmada");

        when(citaMedicaRepository.findById(1L)).thenReturn(Optional.of(citaMedica));
        when(citaMedicaRepository.save(any(CitaMedica.class))).thenReturn(citaMedica);

        CitaMedica resultado = citaMedicaService.patchCitaMedica(actualizacion);

        assertNotNull(resultado);
        verify(citaMedicaRepository, times(1)).save(any(CitaMedica.class));
    }

    @Test
    void patchCitaMedica_conIdInexistente_debeRetornarNull() {
        CitaMedica actualizacion = new CitaMedica();
        actualizacion.setId(99L);

        when(citaMedicaRepository.findById(99L)).thenReturn(Optional.empty());

        CitaMedica resultado = citaMedicaService.patchCitaMedica(actualizacion);

        assertNull(resultado);
        verify(citaMedicaRepository, never()).save(any());
    }

    @Test
    void deleteById_debeEliminarCitaMedica() {
        doNothing().when(citaMedicaRepository).deleteById(1L);

        citaMedicaService.deleteById(1L);

        verify(citaMedicaRepository, times(1)).deleteById(1L);
    }
}