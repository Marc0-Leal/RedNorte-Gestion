package RedNorte.Gestion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import RedNorte.Gestion.model.Medico;
import RedNorte.Gestion.repository.MedicoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;

    private Medico medico;

    @BeforeEach
    void setUp() {
        medico = new Medico();
        medico.setId(1L);
        medico.setNombre("María");
        medico.setApellido("López");
        medico.setEspecialidad("Cardiología");
        medico.setTelefono(912345678);
        medico.setCorreo("maria@hospital.com");
    }

    @Test
    void findAll_debeRetornarListaDeMedicos() {
        List<Medico> lista = Arrays.asList(medico, new Medico());
        when(medicoRepository.findAll()).thenReturn(lista);

        List<Medico> resultado = medicoService.findAll();

        assertEquals(2, resultado.size());
        verify(medicoRepository, times(1)).findAll();
    }

    @Test
    void findById_conIdExistente_debeRetornarMedico() {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));

        Medico resultado = medicoService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Cardiología", resultado.getEspecialidad());
        verify(medicoRepository, times(1)).findById(1L);
    }

    @Test
    void findById_conIdInexistente_debeRetornarNull() {
        when(medicoRepository.findById(99L)).thenReturn(Optional.empty());

        Medico resultado = medicoService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarMedico() {
        when(medicoRepository.save(medico)).thenReturn(medico);

        Medico resultado = medicoService.save(medico);

        assertNotNull(resultado);
        assertEquals("María", resultado.getNombre());
        verify(medicoRepository, times(1)).save(medico);
    }

    @Test
    void patchMedico_conIdExistente_debeActualizarCampos() {
        Medico actualizacion = new Medico();
        actualizacion.setId(1L);
        actualizacion.setNombre("Ana");
        actualizacion.setApellido("García");
        actualizacion.setEspecialidad("Neurología");
        actualizacion.setTelefono(987654321);
        actualizacion.setCorreo("ana@hospital.com");

        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico));
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);

        Medico resultado = medicoService.patchMedico(actualizacion);

        assertNotNull(resultado);
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

    @Test
    void patchMedico_conIdInexistente_debeRetornarNull() {
        Medico actualizacion = new Medico();
        actualizacion.setId(99L);

        when(medicoRepository.findById(99L)).thenReturn(Optional.empty());

        Medico resultado = medicoService.patchMedico(actualizacion);

        assertNull(resultado);
        verify(medicoRepository, never()).save(any());
    }

    @Test
    void deleteById_debeEliminarMedico() {
        doNothing().when(medicoRepository).deleteById(1L);

        medicoService.deleteById(1L);

        verify(medicoRepository, times(1)).deleteById(1L);
    }
}