package com.example.demo.Controller;

import com.example.demo.Dto.SoinDTO;
import com.example.demo.Service.SoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.SetOfIntegerSyntax;
import java.util.List;
import java.util.Optional;
@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "Soin", description = "Gestion des soins")
@RequestMapping("/soins")
public class SoinController {

    private final SoinService soinService;

    public SoinController(SoinService soinService){
        this.soinService = soinService;
    }

    @Operation(summary = "Récupérer tous les soins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des soins récupérée"),
            @ApiResponse(responseCode = "403", description = "Accès interdit")
    })
    @GetMapping
    public List<SoinDTO> findAll(){
        return soinService.findAll();
    }

    @Operation(summary = "Récupérer un soin par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soin trouvé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Soin non trouvé")
    })
    @GetMapping("/{id}")
    public Optional<SoinDTO> findById(@PathVariable Long id){
        return soinService.findById(id);
    }

    @Operation(summary = "Créer un nouveau soin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Soin créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit")
    })
    @PostMapping("")
    public SoinDTO createSoin(@RequestBody SoinDTO soinDTO){
        return soinService.createSoin(soinDTO);
    }

    @Operation(summary = "Mettre à jour un soin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Soin mis à jour"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Soin non trouvé")
    })
    @PutMapping("/{id}")
    public SoinDTO updateSoin(@PathVariable Long id, @RequestBody SoinDTO soinDTO){
        return soinService.updateSoin(id, soinDTO);
    }

    @Operation(summary = "Supprimer un soin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Soin supprimé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Soin non trouvé")
    })
    @DeleteMapping("/{id}")
    public void deleteSoin(@PathVariable Long id){
        soinService.deleteSoin(id);
    }

}
