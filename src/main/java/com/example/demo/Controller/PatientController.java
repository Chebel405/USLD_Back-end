package com.example.demo.Controller;

import com.example.demo.Dto.PatientRechercheDTO;

import com.example.demo.Service.PatientServiceGlobal;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Patients (global)", description = "Recherche globale parmi tous les patients")
public class PatientController {

    private final PatientServiceGlobal service;

    public PatientController(PatientServiceGlobal service) {
        this.service = service;
    }

    @PostMapping("/search")
    public List<PatientRechercheDTO> search(@RequestBody PatientRechercheDTO request) {
        return service.search(request);
    }

}
