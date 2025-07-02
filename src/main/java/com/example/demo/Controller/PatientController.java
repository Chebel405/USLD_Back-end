package com.example.demo.Controller;

import com.example.demo.Dto.PatientDTO;
import com.example.demo.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Patient", description = "Gestion des patients")
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){

        this.patientService = patientService;
    }

    @Operation(summary = "Récupérer tous les patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des patients récupérée avec succès"),
            @ApiResponse(responseCode = "403", description = "Accès interdit - Authentification requise")
    })
    @GetMapping
    public List<PatientDTO> findAll(){
        return patientService.findAll();}

    @Operation(summary = "Récupérer un patient par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient trouvé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit - Authentification requise"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @GetMapping("/{id}")
    public Optional<PatientDTO> findById(@PathVariable Long id){
        return patientService.findById(id);}

    @Operation(summary = "Créer un patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit - Authentification requise")
    })
    @PostMapping("")
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO){
        return patientService.createPatient(patientDTO);
    }

    @Operation(summary = "Mettre à jour un patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient mis à jour"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit - Authentification requise"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO){
        return patientService.updatePatient(id, patientDTO);
    }

    @Operation(summary = "Supprimer un patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Patient supprimé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit - Authentification requise"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
    }
}