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

import RedNorte.Gestion.model.Pago;
import RedNorte.Gestion.service.PagoService;

@RestController
@RequestMapping("/api/pago")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<Pago>> getAllCitasMedicas() {
        List<Pago> pago = pagoService.findAll();
        if (pago.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pago);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Long id) {
        Pago citaMedica = pagoService.findById(id);
        if (citaMedica == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(citaMedica);
    }

    @PostMapping
    public ResponseEntity<Pago> createPago(@RequestBody Pago citaMedica) {
        Pago createdPago = pagoService.save(citaMedica);
        return ResponseEntity.status(201).body(createdPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> updatePago(@PathVariable Long id, @RequestBody Pago citaMedica) {
        citaMedica.setId(id);
        Pago updatedPago = pagoService.save(citaMedica);
        if (updatedPago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPago);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pago> patchPago(@PathVariable Long id, @RequestBody Pago citaMedica) {
        citaMedica.setId(id);
        Pago patchedPago = pagoService.patchPago(citaMedica);
        if (patchedPago == null) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(patchedPago);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
