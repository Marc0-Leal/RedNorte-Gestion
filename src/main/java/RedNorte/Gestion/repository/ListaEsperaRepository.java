package RedNorte.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RedNorte.Gestion.model.ListaEspera;

@Repository
public interface ListaEsperaRepository extends JpaRepository<ListaEspera, Long>{

}
