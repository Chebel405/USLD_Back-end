package com.example.demo.Controller;

import com.example.demo.Dto.PatientSansSoinDTO;
import com.example.demo.Service.PatientSansSoinService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients/sanssoin")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Patients Sans Soin", description = "Gestion des patients sans soins")
public class PatientSansSoinController {

    private final PatientSansSoinService service;

    public PatientSansSoinController(PatientSansSoinService service) {
        this.service = service;
    }

    @GetMapping
    public List<PatientSansSoinDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PatientSansSoinDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public PatientSansSoinDTO create(@RequestBody PatientSansSoinDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PatientSansSoinDTO update(@PathVariable Long id, @RequestBody PatientSansSoinDTO dto) {
        return service.update(id, dto);
    }
    @GetMapping("/search/nom")
    public ResponseEntity<List<PatientSansSoinDTO>> findByNom(@RequestParam String nom) {
        return ResponseEntity.ok(service.findByNom(nom));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
