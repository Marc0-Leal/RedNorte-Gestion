package RedNorte.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RedNorte.Gestion.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

}
