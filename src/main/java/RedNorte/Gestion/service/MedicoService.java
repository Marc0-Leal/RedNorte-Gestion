package RedNorte.Gestion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RedNorte.Gestion.model.Medico;
import RedNorte.Gestion.repository.MedicoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    public Medico findById(Long id) {
        Medico Medico = medicoRepository.findById(id).orElse(null);
        return Medico;
    }

    public List<Medico> findByHospital(Long hospitalId) {
        return medicoRepository.findByHospitalId(hospitalId);
    }

    public Medico save(Medico citaMedica) {
        return medicoRepository.save(citaMedica);
    }

    public Medico patchMedico(Medico citaMedica) {
        Medico existingMedico = medicoRepository.findById(citaMedica.getId()).orElse(null);
        if (existingMedico != null) {
            if (citaMedica.getNombre() != null) {
                existingMedico.setNombre(citaMedica.getNombre());
            }
            if (citaMedica.getApellido() != null) {
                existingMedico.setApellido(citaMedica.getApellido());
            }
            if (citaMedica.getEspecialidad() != null) {
                existingMedico.setEspecialidad(citaMedica.getEspecialidad());
            }
            if (citaMedica.getTelefono() != null) {
                existingMedico.setTelefono(citaMedica.getTelefono());
            }
            if (citaMedica.getCorreo() != null) {
                existingMedico.setCorreo(citaMedica.getCorreo());
            }
            return medicoRepository.save(existingMedico);
        }
        return null;
    }

    public void deleteById(Long id) {
        medicoRepository.deleteById(id);
    }
}