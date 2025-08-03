package com.example.demo.Controller;

import com.example.demo.Dto.PatientAlzheimerDTO;
import com.example.demo.Service.PatientAlzheimerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patients/alzheimer")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Patients Alzheimer", description = "Gestion des patients Alzheimer")
public class PatientAlzheimerController {

    private final PatientAlzheimerService service;

    public PatientAlzheimerController(PatientAlzheimerService service) {
        this.service = service;
    }

    @GetMapping
    public List<PatientAlzheimerDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PatientAlzheimerDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public PatientAlzheimerDTO create(@RequestBody PatientAlzheimerDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PatientAlzheimerDTO update(@PathVariable Long id, @RequestBody PatientAlzheimerDTO dto) {
        return service.update(id, dto);
    }
    @GetMapping("/search/nom")
    public ResponseEntity<List<PatientAlzheimerDTO>> findByNom(@RequestParam String nom) {
        return ResponseEntity.ok(service.findByNom(nom));
    }
    @GetMapping("/search/prenom")
    public List<PatientAlzheimerDTO>findByPrenom(@RequestParam String prenom){
        return service.findByPrenom(prenom);
    }


    @GetMapping("/search/date-naissance")
    public List<PatientAlzheimerDTO>findByDateNaissance(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                   LocalDate dateNaissance){
        return service.findByDateNaissance(dateNaissance);
    }
    @GetMapping("/search/chambre")
    public List<PatientAlzheimerDTO>findByNumeroChambre(@RequestParam Integer numeroChambre){
        return service.findByNumeroChambre(numeroChambre);
    }
    @GetMapping("/search/autonomie")
    public List<PatientAlzheimerDTO>findByNiveauAutonomie(@RequestParam String niveauAutonomie){
        return service.findByNiveauAutonomie(niveauAutonomie);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
