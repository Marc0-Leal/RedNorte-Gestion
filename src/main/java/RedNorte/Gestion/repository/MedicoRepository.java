package RedNorte.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RedNorte.Gestion.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

}