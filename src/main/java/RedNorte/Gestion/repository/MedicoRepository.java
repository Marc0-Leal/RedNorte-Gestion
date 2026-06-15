package RedNorte.Gestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RedNorte.Gestion.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

    List<Medico> findByHospitalId(Long hospitalId);
}
