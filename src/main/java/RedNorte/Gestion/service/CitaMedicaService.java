package RedNorte.Gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RedNorte.Gestion.model.CitaMedica;
import RedNorte.Gestion.model.Cliente;
import RedNorte.Gestion.model.ListaEspera;
import RedNorte.Gestion.model.Pago;
import RedNorte.Gestion.repository.CitaMedicaRepository;
import RedNorte.Gestion.repository.ListaEsperaRepository;
import RedNorte.Gestion.repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CitaMedicaService {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    public List<CitaMedica> findAll() {
        return citaMedicaRepository.findAll();
    }

    public CitaMedica findById(Long id) {
        CitaMedica CitaMedica = citaMedicaRepository.findById(id).orElse(null);
        return CitaMedica;
    }

    public CitaMedica save(CitaMedica citaMedica) {
        return citaMedicaRepository.save(citaMedica);
    }

    public CitaMedica patchCitaMedica(CitaMedica citaMedica) {
        CitaMedica existingCitaMedica = citaMedicaRepository.findById(citaMedica.getId()).orElse(null);
        if (existingCitaMedica != null) {
            if (citaMedica.getFecha() != null) {
                existingCitaMedica.setFecha(citaMedica.getFecha());
            }
            if (citaMedica.getHora() != null) {
                existingCitaMedica.setHora(citaMedica.getHora());
            }
            if (citaMedica.getEstado() != null) {
                existingCitaMedica.setEstado(citaMedica.getEstado());
            }
            if (citaMedica.getSintomas() != null) {
                existingCitaMedica.setSintomas(citaMedica.getSintomas());
            }
            return citaMedicaRepository.save(existingCitaMedica);
        }
        return null;
    }

    public List<CitaMedica> findByCliente(Cliente cliente) {
        List<CitaMedica> citaMedica = citaMedicaRepository.findByCliente(cliente);
        if (citaMedica != null) {
            return citaMedicaRepository.findByCliente(cliente);
        }
        return null;
    }

    public void deleteById(Long id) {
        CitaMedica cita = citaMedicaRepository.findById(id).orElse(null);
        if (cita != null) {
            Pago pago = cita.getPago();
            ListaEspera listaEspera = cita.getListaEspera();

            cita.setPago(null);
            cita.setListaEspera(null);
            cita.setMedico(null);
            cita.setCliente(null);
            citaMedicaRepository.save(cita);
            citaMedicaRepository.deleteById(id);

            if (pago != null) pagoRepository.deleteById(pago.getId());
            if (listaEspera != null) listaEsperaRepository.deleteById(listaEspera.getId());
        }
    }
}