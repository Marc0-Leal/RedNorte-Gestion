package RedNorte.Gestion.service;

import org.springframework.beans.factory.annotation.Autowired;

import RedNorte.Gestion.model.Region;
import RedNorte.Gestion.repository.RegionRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }
    
    public Region findById(Long id) {
        Region Region = regionRepository.findById(id).orElse(null);
        return Region;
    }

    public Region save(Region citaMedica) {
        return regionRepository.save(citaMedica);
    }

    public Region patchRegion(Region citaMedica) {
    Region existingRegion = regionRepository.findById(citaMedica.getId()).orElse(null);
    if (existingRegion != null) {
        if (citaMedica.getNombre() != null) {
            existingRegion.setNombre(citaMedica.getNombre());
        }
        return regionRepository.save(existingRegion);
    }
    return null;
}

    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }
}
