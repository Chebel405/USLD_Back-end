package com.example.demo.Controller;

import com.example.demo.Dto.PatientUSLDDTO;
import com.example.demo.Service.PatientUSLDService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients/usld")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Patients USLD", description = "Gestion des patients en USLD")
public class PatientUSLDController {

    private final PatientUSLDService service;

    public PatientUSLDController(PatientUSLDService service) {
        this.service = service;
    }

    @GetMapping
    public List<PatientUSLDDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PatientUSLDDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public PatientUSLDDTO create(@RequestBody PatientUSLDDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PatientUSLDDTO update(@PathVariable Long id, @RequestBody PatientUSLDDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search/nom")
    public List<PatientUSLDDTO> findByNom(@RequestParam String nom) {
        return service.findByNom(nom);
    }

}
