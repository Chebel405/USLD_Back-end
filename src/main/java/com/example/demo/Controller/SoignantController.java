package com.example.demo.Controller;

import com.example.demo.Dto.SoignantDTO;
import com.example.demo.Service.SoignantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "Soignant", description = "Gestion des soignants")
@RequestMapping("/soignants")
public class SoignantController {
    private final SoignantService soignantService;

    public SoignantController(SoignantService soignantService) {
        this.soignantService = soignantService;
    }

    @Operation(summary = "Récupérer tous les soignants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des soignants récupérée"),
            @ApiResponse(responseCode = "403", description = "Accès interdit")
    })
    @GetMapping
    public List<SoignantDTO> findAll(){
        return soignantService.findAll();}

    @Operation(summary = "Récupérer un soignant par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soignant trouvé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Soignant non trouvé")
    })
    @GetMapping("/{id}")
    public Optional<SoignantDTO> findById(@PathVariable Long id){
        return soignantService.findById(id);}

    @Operation(summary = "Créer un nouveau soignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Soignant créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit")
    })
    @PostMapping("")
    public SoignantDTO createSoignant(@RequestBody SoignantDTO soignantDTO){
        return soignantService.createSoignant(soignantDTO);
    }

    @Operation(summary = "Mettre à jour un soignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soignant mis à jour"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Soignant non trouvé")
    })
    @PutMapping("/{id}")
    public SoignantDTO updateSoignant(@PathVariable Long id, @RequestBody SoignantDTO soignantDTO){
        return soignantService.updateSoignant(id, soignantDTO);
    }

    @Operation(summary = "Supprimer un soignant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Soignant supprimé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Soignant non trouvé")
    })
    @DeleteMapping("/{id}")
    public void deleteSoignant(@PathVariable Long id){
        soignantService.deleteSoignant(id);
    }
}