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

import RedNorte.Gestion.model.Comuna;
import RedNorte.Gestion.service.ComunaService;

@RestController
@RequestMapping("/api/comuna")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> getAllCitasMedicas() {
        List<Comuna> comuna = comunaService.findAll();
        if (comuna.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comuna);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> getComunaById(@PathVariable Long id) {
        Comuna citaMedica = comunaService.findById(id);
        if (citaMedica == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(citaMedica);
    }

    @PostMapping
    public ResponseEntity<Comuna> createComuna(@RequestBody Comuna citaMedica) {
        Comuna createdComuna = comunaService.save(citaMedica);
        return ResponseEntity.status(201).body(createdComuna);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> updateComuna(@PathVariable Long id, @RequestBody Comuna citaMedica) {
        citaMedica.setId(id);
        Comuna updatedComuna = comunaService.save(citaMedica);
        if (updatedComuna == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedComuna);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> patchComuna(@PathVariable Long id, @RequestBody Comuna citaMedica) {
        citaMedica.setId(id);
        Comuna patchedComuna = comunaService.patchComuna(citaMedica);
        if (patchedComuna == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(patchedComuna);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComuna(@PathVariable Long id) {
        comunaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
