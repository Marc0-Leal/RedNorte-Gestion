package RedNorte.Gestion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RedNorte.Gestion.model.Hospital;
import RedNorte.Gestion.repository.HospitalRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }
    
    public Hospital findById(Long id) {
        Hospital Hospital = hospitalRepository.findById(id).orElse(null);
        return Hospital;
    }

    public Hospital save(Hospital citaMedica) {
        return hospitalRepository.save(citaMedica);
    }

    public Hospital patchHospital(Hospital citaMedica) {
    Hospital existingHospital = hospitalRepository.findById(citaMedica.getId()).orElse(null);
    if (existingHospital != null) {
        if (citaMedica.getNombre() != null) {
            existingHospital.setNombre(citaMedica.getNombre());
        }
        if (citaMedica.getDireccion() != null) { 
            existingHospital.setDireccion(citaMedica.getDireccion());
        }
        if (citaMedica.getTelefono() != null) {
            existingHospital.setTelefono(citaMedica.getTelefono());
        }
        return hospitalRepository.save(existingHospital);
    }
    return null;
}

    public void deleteById(Long id) {
        hospitalRepository.deleteById(id);
    }
}
