package RedNorte.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RedNorte.Gestion.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>{
    
}
