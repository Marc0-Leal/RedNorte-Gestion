package RedNorte.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RedNorte.Gestion.model.CitaMedica;
import RedNorte.Gestion.model.Cliente;

import java.util.List;


@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long>{

    List<CitaMedica> findByCliente(Cliente cliente);

}
