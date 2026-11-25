package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.VehiculeDTO;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    public ResponseEntity<VehiculeDTO> create(@Valid @RequestBody VehiculeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehiculeDTO>> findAll() {
        return ResponseEntity.ok(vehiculeService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeDTO> update(@PathVariable Long id, @Valid @RequestBody VehiculeDTO dto) {
        return ResponseEntity.ok(vehiculeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    public ResponseEntity<VehiculeDTO> affecterVehiculeAEquipement(@PathVariable Long idVehicule,
                                                                   @PathVariable Long idEquipement) {
        return ResponseEntity.ok(vehiculeService.affecterVehiculeAEquipement(idVehicule, idEquipement));
    }

    @PostMapping("/maintenance")
    public ResponseEntity<VehiculeDTO> affecterMaintenanceAvehicule(@RequestParam String description,
                                                                    @RequestParam String immatriculation) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeService.affecterMaintenanceAvehicule(description, immatriculation));
    }
}
