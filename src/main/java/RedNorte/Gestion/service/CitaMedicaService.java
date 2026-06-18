package RedNorte.Gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RedNorte.Gestion.model.CitaMedica;
import RedNorte.Gestion.model.Cliente;
import RedNorte.Gestion.repository.CitaMedicaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CitaMedicaService {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

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
            if (citaMedica.getMedico() != null) {
                existingCitaMedica.setMedico(citaMedica.getMedico());
            }
            return citaMedicaRepository.save(existingCitaMedica);
        }
        return null;
    }

    public List<CitaMedica> findByCliente(Cliente cliente){
        List<CitaMedica> citaMedica = citaMedicaRepository.findByCliente(cliente);
        if (citaMedica != null) {
            return citaMedicaRepository.findByCliente(cliente);
        }
        return null;
    }

    public void deleteById(Long id) {
        citaMedicaRepository.deleteById(id);
    }
}