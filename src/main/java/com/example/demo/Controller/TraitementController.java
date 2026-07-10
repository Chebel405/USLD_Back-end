package com.example.demo.Controller;

import com.example.demo.Dto.TraitementDTO;
import com.example.demo.Service.TraitementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "Traitement", description = "Gestion des traitements")
@RequestMapping("/traitements")
public class TraitementController {

    private final TraitementService traitementService;

    public TraitementController(TraitementService traitementService) {
        this.traitementService = traitementService;
    }
    @Operation(summary = "Récupérer tous les traitements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des traitements récupérée"),
            @ApiResponse(responseCode = "403", description = "Accès interdit")
    })
    @GetMapping
    public List<TraitementDTO> findAll() {
        return traitementService.findAll();
    }

    @Operation(summary = "Récupérer un traitement par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Traitement trouvé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Traitement non trouvé")
    })
    @GetMapping("/{id}")
    public TraitementDTO getTraitementById(@PathVariable Long id) {
        return traitementService.getTraitementById(id);
    }

    @Operation(summary = "Récupérer les traitements d'un patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Traitements du patient récupérés"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @GetMapping("/patient/{patientId}")
    public List<TraitementDTO> getTraitementsByPatientId(@PathVariable Long patientId) {
        return traitementService.getTraitementsByPatientId(patientId);
    }

    @Operation(summary = "Créer un nouveau traitement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Traitement créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Patient non trouvé")
    })
    @PostMapping
    public TraitementDTO createTraitement(@RequestBody TraitementDTO traitementDTO) {
        return traitementService.createTraitement(traitementDTO);
    }

    @Operation(summary = "Mettre à jour un traitement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Traitement mis à jour"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Traitement ou patient non trouvé")
    })
    @PutMapping("/{id}")
    public TraitementDTO updateTraitement(
            @PathVariable Long id,
            @RequestBody TraitementDTO traitementDTO) {

        return traitementService.updateTraitement(id, traitementDTO);
    }

    @Operation(summary = "Supprimer un traitement")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Traitement supprimé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Traitement non trouvé")
    })
    @DeleteMapping("/{id}")
    public void deleteTraitement(@PathVariable Long id) {
        traitementService.deleteTraitement(id);
    }


}
