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

import RedNorte.Gestion.model.Hospital;
import RedNorte.Gestion.service.HospitalService;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<List<Hospital>> getAllCitasMedicas() {
        List<Hospital> hospital = hospitalService.findAll();
        if (hospital.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(hospital);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) {
        Hospital citaMedica = hospitalService.findById(id);
        if (citaMedica == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(citaMedica);
    }

    @PostMapping
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital citaMedica) {
        Hospital createdHospital = hospitalService.save(citaMedica);
        return ResponseEntity.status(201).body(createdHospital);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable Long id, @RequestBody Hospital citaMedica) {
        citaMedica.setId(id);
        Hospital updatedHospital = hospitalService.save(citaMedica);
        if (updatedHospital == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedHospital);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Hospital> patchHospital(@PathVariable Long id, @RequestBody Hospital citaMedica) {
        citaMedica.setId(id);
        Hospital patchedHospital = hospitalService.patchHospital(citaMedica);
        if (patchedHospital == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(patchedHospital);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        hospitalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
