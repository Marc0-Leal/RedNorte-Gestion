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

import RedNorte.Gestion.model.Medico;
import RedNorte.Gestion.service.MedicoService;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> getAllCitasMedicas() {
        List<Medico> medico = medicoService.findAll();
        if (medico.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable Long id) {
        Medico citaMedica = medicoService.findById(id);
        if (citaMedica == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(citaMedica);
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<Medico>> getMedicosByHospital(@PathVariable Long hospitalId) {
        List<Medico> medicos = medicoService.findByHospital(hospitalId);
        return ResponseEntity.ok(medicos);
    }

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico citaMedica) {
        Medico createdMedico = medicoService.save(citaMedica);
        return ResponseEntity.status(201).body(createdMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(@PathVariable Long id, @RequestBody Medico citaMedica) {
        citaMedica.setId(id);
        Medico updatedMedico = medicoService.save(citaMedica);
        if (updatedMedico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMedico);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Medico> patchMedico(@PathVariable Long id, @RequestBody Medico citaMedica) {
        citaMedica.setId(id);
        Medico patchedMedico = medicoService.patchMedico(citaMedica);
        if (patchedMedico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patchedMedico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        medicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}