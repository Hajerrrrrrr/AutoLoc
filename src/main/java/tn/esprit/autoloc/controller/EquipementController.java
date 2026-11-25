package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.EquipementDTO;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;

    @PostMapping
    public ResponseEntity<EquipementDTO> create(@Valid @RequestBody EquipementDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipementDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(equipementService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EquipementDTO>> findAll() {
        return ResponseEntity.ok(equipementService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipementDTO> update(@PathVariable Long id, @Valid @RequestBody EquipementDTO dto) {
        return ResponseEntity.ok(equipementService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
