package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.ReservationRequestDTO;
import tn.esprit.autoloc.dto.ReservationResponseDTO;
import tn.esprit.autoloc.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> create(@Valid @RequestBody ReservationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> update(@PathVariable Long id,
                                                         @Valid @RequestBody ReservationRequestDTO dto) {
        return ResponseEntity.ok(reservationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idReservation}/client")
    public ResponseEntity<ReservationResponseDTO> affecterReservationAClient(@PathVariable Long idReservation,
                                                                             @RequestParam String nom,
                                                                             @RequestParam String prenom) {
        return ResponseEntity.ok(reservationService.affecterReservationAClient(idReservation, nom, prenom));
    }

    @PostMapping("/avec-contrat")
    public ResponseEntity<ReservationResponseDTO> creerReservationEtContratAssocie(
            @Valid @RequestBody ReservationWithContratRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.creerReservationEtContratAssocie(requestDTO));
    }
}
