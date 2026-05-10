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

import RedNorte.Gestion.model.ListaEspera;
import RedNorte.Gestion.service.ListaEsperaService;

@RestController
@RequestMapping("/api/listaEspera")
public class ListaEsperaController {
    @Autowired
    private ListaEsperaService listaEsperaService;

    @GetMapping
    public ResponseEntity<List<ListaEspera>> getAllCitasMedicas() {
        List<ListaEspera> listaEspera = listaEsperaService.findAll();
        if (listaEspera.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaEspera);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaEspera> getListaEsperaById(@PathVariable Long id) {
        ListaEspera citaMedica = listaEsperaService.findById(id);
        if (citaMedica == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(citaMedica);
    }

    @PostMapping
    public ResponseEntity<ListaEspera> createListaEspera(@RequestBody ListaEspera citaMedica) {
        ListaEspera createdListaEspera = listaEsperaService.save(citaMedica);
        return ResponseEntity.status(201).body(createdListaEspera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListaEspera> updateListaEspera(@PathVariable Long id, @RequestBody ListaEspera citaMedica) {
        citaMedica.setId(id);
        ListaEspera updatedListaEspera = listaEsperaService.save(citaMedica);
        if (updatedListaEspera == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedListaEspera);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ListaEspera> patchListaEspera(@PathVariable Long id, @RequestBody ListaEspera citaMedica) {
        citaMedica.setId(id);
        ListaEspera patchedListaEspera = listaEsperaService.patchListaEspera(citaMedica);
        if (patchedListaEspera == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(patchedListaEspera);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListaEspera(@PathVariable Long id) {
        listaEsperaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
