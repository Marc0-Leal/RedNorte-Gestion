package RedNorte.Gestion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RedNorte.Gestion.model.ListaEspera;
import RedNorte.Gestion.repository.ListaEsperaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ListaEsperaService {
    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    public List<ListaEspera> findAll() {
        return listaEsperaRepository.findAll();
    }
    
    public ListaEspera findById(Long id) {
        ListaEspera ListaEspera = listaEsperaRepository.findById(id).orElse(null);
        return ListaEspera;
    }

    public ListaEspera save(ListaEspera citaMedica) {
        return listaEsperaRepository.save(citaMedica);
    }

    public ListaEspera patchListaEspera(ListaEspera citaMedica) {
    ListaEspera existingListaEspera = listaEsperaRepository.findById(citaMedica.getId()).orElse(null);
    if (existingListaEspera != null) {
        if (citaMedica.getFecha_solitud() != null) {
            existingListaEspera.setFecha_solitud(citaMedica.getFecha_solitud());
        }
        if (citaMedica.getPrioridad() != null) { 
            existingListaEspera.setPrioridad(citaMedica.getPrioridad());
        }
        return listaEsperaRepository.save(existingListaEspera);
    }
    return null;
}

    public void deleteById(Long id) {
        listaEsperaRepository.deleteById(id);
    }
}
