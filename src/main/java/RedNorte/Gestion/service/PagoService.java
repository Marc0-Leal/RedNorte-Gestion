package RedNorte.Gestion.service;

import org.springframework.beans.factory.annotation.Autowired;

import RedNorte.Gestion.model.Pago;
import RedNorte.Gestion.repository.PagoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }
    
    public Pago findById(Long id) {
        Pago Pago = pagoRepository.findById(id).orElse(null);
        return Pago;
    }

    public Pago save(Pago citaMedica) {
        return pagoRepository.save(citaMedica);
    }

    public Pago patchPago(Pago citaMedica) {
    Pago existingPago = pagoRepository.findById(citaMedica.getId()).orElse(null);
    if (existingPago != null) {
        if (citaMedica.getMonto() != null) {
            existingPago.setMonto(citaMedica.getMonto());
        }
        if (citaMedica.getFecha_pago() != null) {
            existingPago.setFecha_pago(citaMedica.getFecha_pago());
        }
        if (citaMedica.getMetodo_pago() != null) {
            existingPago.setMetodo_pago(citaMedica.getMetodo_pago());
        }
        if (citaMedica.getEstado() != null) {
            existingPago.setEstado(citaMedica.getEstado());
        }
        return pagoRepository.save(existingPago);
    }
    return null;
}

    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }
}
