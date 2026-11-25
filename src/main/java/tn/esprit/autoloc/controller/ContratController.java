package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ContratDTO;
import tn.esprit.autoloc.service.IContratService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @PostMapping
    public ResponseEntity<ContratDTO> create(@Valid @RequestBody ContratDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContratDTO>> findAll() {
        return ResponseEntity.ok(contratService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratDTO> update(@PathVariable Long id, @Valid @RequestBody ContratDTO dto) {
        return ResponseEntity.ok(contratService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/affecter-reservation")
    public ResponseEntity<ContratDTO> affecterContratAReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSignature,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contratService.affecterContratAReservation(dateSignature, dateDebut, dateFin));
    }
}
