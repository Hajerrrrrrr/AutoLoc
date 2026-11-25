package tn.esprit.autoloc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.PaiementDTO;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping("/{id}")
    public ResponseEntity<PaiementDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaiementDTO>> findAll() {
        return ResponseEntity.ok(paiementService.findAll());
    }
}
