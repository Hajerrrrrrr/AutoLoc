package tn.esprit.autoloc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.dto.EmployeDTO;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @PostMapping
    public ResponseEntity<EmployeDTO> create(@Valid @RequestBody EmployeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeDTO>> findAll() {
        return ResponseEntity.ok(employeService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeDTO> update(@PathVariable Long id, @Valid @RequestBody EmployeDTO dto) {
        return ResponseEntity.ok(employeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
