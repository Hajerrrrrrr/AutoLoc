package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.MaintenanceDTO;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<MaintenanceDTO> create(@Valid @RequestBody MaintenanceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceDTO>> findAll() {
        return ResponseEntity.ok(maintenanceService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> update(@PathVariable Long id, @Valid @RequestBody MaintenanceDTO dto) {
        return ResponseEntity.ok(maintenanceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
