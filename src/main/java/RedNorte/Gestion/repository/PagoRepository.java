package RedNorte.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import RedNorte.Gestion.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long>{
    
}
