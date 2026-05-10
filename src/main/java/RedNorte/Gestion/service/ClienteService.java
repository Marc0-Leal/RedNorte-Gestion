package RedNorte.Gestion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RedNorte.Gestion.model.Cliente;
import RedNorte.Gestion.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    
    public Cliente findById(Long id) {
        Cliente Cliente = clienteRepository.findById(id).orElse(null);
        return Cliente;
    }

    public Cliente save(Cliente citaMedica) {
        return clienteRepository.save(citaMedica);
    }

    public Cliente patchCliente(Cliente citaMedica) {
    Cliente existingCliente = clienteRepository.findById(citaMedica.getId()).orElse(null);
    if (existingCliente != null) {
        if (citaMedica.getNombre() != null) {
            existingCliente.setNombre(citaMedica.getNombre());
        }
        if (citaMedica.getApellido() != null) {
            existingCliente.setApellido(citaMedica.getApellido());
        }
        if (citaMedica.getRut() != null) { 
            existingCliente.setRut(citaMedica.getRut());
        }
        if (citaMedica.getTelefono() != null) {
            existingCliente.setTelefono(citaMedica.getTelefono());
        }
        if (citaMedica.getCorreo() != null) {
            existingCliente.setCorreo(citaMedica.getCorreo());
        }
        if (citaMedica.getDireccion() != null) { 
            existingCliente.setDireccion(citaMedica.getDireccion());
        }
        return clienteRepository.save(existingCliente);
    }
    return null;
}

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
