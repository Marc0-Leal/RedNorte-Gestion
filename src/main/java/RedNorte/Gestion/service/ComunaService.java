package RedNorte.Gestion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RedNorte.Gestion.model.Comuna;
import RedNorte.Gestion.repository.ComunaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ComunaService {
    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> findAll() {
        return comunaRepository.findAll();
    }
    
    public Comuna findById(Long id) {
        Comuna Comuna = comunaRepository.findById(id).orElse(null);
        return Comuna;
    }

    public Comuna save(Comuna citaMedica) {
        return comunaRepository.save(citaMedica);
    }

    public Comuna patchComuna(Comuna citaMedica) {
    Comuna existingComuna = comunaRepository.findById(citaMedica.getId()).orElse(null);
    if (existingComuna != null) {
        if (citaMedica.getNombre() != null) {
            existingComuna.setNombre(citaMedica.getNombre());
        }
        return comunaRepository.save(existingComuna);
    }
    return null;
}

    public void deleteById(Long id) {
        comunaRepository.deleteById(id);
    }
}
