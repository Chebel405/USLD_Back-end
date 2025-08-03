package com.example.demo.Controller;

import com.example.demo.Dto.PatientUSLDDTO;
import com.example.demo.Service.PatientUSLDService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/search/prenom")
    public List<PatientUSLDDTO>findByPrenom(@RequestParam String prenom){
        return service.findByPrenom(prenom);
    }
    @GetMapping("/search/date-naissance")
    public List<PatientUSLDDTO>findByDateNaissance(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                   LocalDate dateNaissance){
        return service.findByDateNaissance(dateNaissance);
    }
    @GetMapping("/search/chambre")
    public List<PatientUSLDDTO>findByNumeroChambre(@RequestParam Integer numeroChambre){
        return service.findByNumeroChambre(numeroChambre);
    }
    @GetMapping("/search/autonomie")
    public List<PatientUSLDDTO>findByNiveauAutonomie(@RequestParam String niveauAutonomie){
        return service.findByNiveauAutonomie(niveauAutonomie);
    }


}
