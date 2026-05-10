package RedNorte.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RedNorte.Gestion.model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long>{
    
}
