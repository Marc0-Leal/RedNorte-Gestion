package RedNorte.Gestion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import RedNorte.Gestion.model.Region;
import RedNorte.Gestion.repository.RegionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionService regionService;

    private Region region;

    @BeforeEach
    void setUp() {
        region = new Region();
        region.setId(1L);
        region.setNombre("Metropolitana");
    }

    @Test
    void findAll_debeRetornarListaDeRegiones() {
        List<Region> lista = Arrays.asList(region, new Region());
        when(regionRepository.findAll()).thenReturn(lista);

        List<Region> resultado = regionService.findAll();

        assertEquals(2, resultado.size());
        verify(regionRepository, times(1)).findAll();
    }

    @Test
    void findById_conIdExistente_debeRetornarRegion() {
        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));

        Region resultado = regionService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Metropolitana", resultado.getNombre());
        verify(regionRepository, times(1)).findById(1L);
    }

    @Test
    void findById_conIdInexistente_debeRetornarNull() {
        when(regionRepository.findById(99L)).thenReturn(Optional.empty());

        Region resultado = regionService.findById(99L);

        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarRegion() {
        when(regionRepository.save(region)).thenReturn(region);

        Region resultado = regionService.save(region);

        assertNotNull(resultado);
        assertEquals("Metropolitana", resultado.getNombre());
        verify(regionRepository, times(1)).save(region);
    }

    @Test
    void patchRegion_conIdExistente_debeActualizarNombre() {
        Region actualizacion = new Region();
        actualizacion.setId(1L);
        actualizacion.setNombre("Valparaíso");

        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));
        when(regionRepository.save(any(Region.class))).thenReturn(region);

        Region resultado = regionService.patchRegion(actualizacion);

        assertNotNull(resultado);
        verify(regionRepository, times(1)).save(any(Region.class));
    }

    @Test
    void patchRegion_conIdInexistente_debeRetornarNull() {
        Region actualizacion = new Region();
        actualizacion.setId(99L);

        when(regionRepository.findById(99L)).thenReturn(Optional.empty());

        Region resultado = regionService.patchRegion(actualizacion);

        assertNull(resultado);
        verify(regionRepository, never()).save(any());
    }

    @Test
    void patchRegion_conNombreNulo_noDebeActualizarNombre() {
        Region actualizacion = new Region();
        actualizacion.setId(1L);
        actualizacion.setNombre(null);

        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));
        when(regionRepository.save(any(Region.class))).thenReturn(region);

        regionService.patchRegion(actualizacion);

        assertEquals("Metropolitana", region.getNombre());
    }

    @Test
    void deleteById_debeEliminarRegion() {
        doNothing().when(regionRepository).deleteById(1L);

        regionService.deleteById(1L);

        verify(regionRepository, times(1)).deleteById(1L);
    }
}