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

import RedNorte.Gestion.model.Region;
import RedNorte.Gestion.service.RegionService;

@RestController
@RequestMapping("/api/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> getAllCitasMedicas() {
        List<Region> region = regionService.findAll();
        if (region.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(region);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        Region citaMedica = regionService.findById(id);
        if (citaMedica == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(citaMedica);
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region citaMedica) {
        Region createdRegion = regionService.save(citaMedica);
        return ResponseEntity.status(201).body(createdRegion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Long id, @RequestBody Region citaMedica) {
        citaMedica.setId(id);
        Region updatedRegion = regionService.save(citaMedica);
        if (updatedRegion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRegion);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> patchRegion(@PathVariable Long id, @RequestBody Region citaMedica) {
        citaMedica.setId(id);
        Region patchedRegion = regionService.patchRegion(citaMedica);
        if (patchedRegion == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(patchedRegion);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
