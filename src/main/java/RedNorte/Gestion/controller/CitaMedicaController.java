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

import RedNorte.Gestion.model.CitaMedica;
import RedNorte.Gestion.service.CitaMedicaService;
@RestController
@RequestMapping("/api/citaMedica")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping
    public ResponseEntity<List<CitaMedica>> getAllCitasMedicas() {
        List<CitaMedica> citaMedicas = citaMedicaService.findAll();
        if (citaMedicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citaMedicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> getCitaMedicaById(@PathVariable Long id) {
        CitaMedica citaMedica = citaMedicaService.findById(id);
        if (citaMedica == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(citaMedica);
    }

    @PostMapping
    public ResponseEntity<CitaMedica> createCitaMedica(@RequestBody CitaMedica citaMedica) {
        CitaMedica createdCitaMedica = citaMedicaService.save(citaMedica);
        return ResponseEntity.status(201).body(createdCitaMedica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> updateCitaMedica(@PathVariable Long id, @RequestBody CitaMedica citaMedica) {
        citaMedica.setId(id);
        CitaMedica updatedCitaMedica = citaMedicaService.save(citaMedica);
        if (updatedCitaMedica == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCitaMedica);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CitaMedica> patchCitaMedica(@PathVariable Long id, @RequestBody CitaMedica citaMedica) {
        citaMedica.setId(id);
        CitaMedica patchedCitaMedica = citaMedicaService.patchCitaMedica(citaMedica);
        if (patchedCitaMedica == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(patchedCitaMedica);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCitaMedica(@PathVariable Long id) {
        citaMedicaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
