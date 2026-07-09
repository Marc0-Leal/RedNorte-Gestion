package RedNorte.Gestion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import RedNorte.Gestion.model.Hospital;
import RedNorte.Gestion.repository.HospitalRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HospitalServiceTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalService hospitalService;

    private Hospital hospital;

    @BeforeEach
    void setUp() {
        hospital = new Hospital();
        hospital.setId(1L);
        hospital.setNombre("Hospital San José");
        hospital.setDireccion("Av. Principal 100");
        hospital.setTelefono(223456789);
    }

    @Test
    void findAll_debeRetornarListaDeHospitales() {
        List<Hospital> lista = Arrays.asList(hospital, new Hospital());
        when(hospitalRepository.findAll()).thenReturn(lista);

        List<Hospital> resultado = hospitalService.findAll();

        assertEquals(2, resultado.size());
        verify(hospitalRepository, times(1)).findAll();
    }

    @Test
    void findById_conIdExistente_debeRetornarHospital() {
        when(hospitalRepository.findById(1L)).thenReturn(Optional.of(hospital));

        Hospital resultado = hospitalService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Hospital San José", resultado.getNombre());
        verify(hospitalRepository, times(1)).findById(1L);
    }

    @Test
    void findById_conIdInexistente_debeRetornarNull() {
        when(hospitalRepository.findById(99L)).thenReturn(Optional.empty());

        Hospital resultado = hospitalService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarHospital() {
        when(hospitalRepository.save(hospital)).thenReturn(hospital);

        Hospital resultado = hospitalService.save(hospital);

        assertNotNull(resultado);
        assertEquals("Av. Principal 100", resultado.getDireccion());
        verify(hospitalRepository, times(1)).save(hospital);
    }

    @Test
    void patchHospital_conIdExistente_debeActualizarCampos() {
        Hospital actualizacion = new Hospital();
        actualizacion.setId(1L);
        actualizacion.setNombre("Hospital Central");
        actualizacion.setDireccion("Calle Nueva 200");
        actualizacion.setTelefono(212345678);

        when(hospitalRepository.findById(1L)).thenReturn(Optional.of(hospital));
        when(hospitalRepository.save(any(Hospital.class))).thenReturn(hospital);

        Hospital resultado = hospitalService.patchHospital(actualizacion);

        assertNotNull(resultado);
        verify(hospitalRepository, times(1)).save(any(Hospital.class));
    }

    @Test
    void patchHospital_conIdInexistente_debeRetornarNull() {
        Hospital actualizacion = new Hospital();
        actualizacion.setId(99L);

        when(hospitalRepository.findById(99L)).thenReturn(Optional.empty());

        Hospital resultado = hospitalService.patchHospital(actualizacion);

        assertNull(resultado);
        verify(hospitalRepository, never()).save(any());
    }

    @Test
    void deleteById_debeEliminarHospital() {
        doNothing().when(hospitalRepository).deleteById(1L);

        hospitalService.deleteById(1L);

        verify(hospitalRepository, times(1)).deleteById(1L);
    }
}