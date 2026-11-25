package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.AgenceDTO;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping
    public ResponseEntity<AgenceDTO> create(@Valid @RequestBody AgenceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenceDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(agenceService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AgenceDTO>> findAll() {
        return ResponseEntity.ok(agenceService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgenceDTO> update(@PathVariable Long id, @Valid @RequestBody AgenceDTO dto) {
        return ResponseEntity.ok(agenceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
