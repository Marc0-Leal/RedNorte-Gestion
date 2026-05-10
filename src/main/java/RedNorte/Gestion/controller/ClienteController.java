package RedNorte.Gestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RedNorte.Gestion.model.Cliente;
import RedNorte.Gestion.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllCitasMedicas() {
        List<Cliente> cliente = clienteService.findAll();
        if (cliente.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente citaMedica = clienteService.findById(id);
        if (citaMedica == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(citaMedica);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente citaMedica) {
        Cliente createdCliente = clienteService.save(citaMedica);
        return ResponseEntity.status(201).body(createdCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente citaMedica) {
        citaMedica.setId(id);
        Cliente updatedCliente = clienteService.save(citaMedica);
        if (updatedCliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCliente);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> patchCliente(@PathVariable Long id, @RequestBody Cliente citaMedica) {
        citaMedica.setId(id);
        Cliente patchedCliente = clienteService.patchCliente(citaMedica);
        if (patchedCliente == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(patchedCliente);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
