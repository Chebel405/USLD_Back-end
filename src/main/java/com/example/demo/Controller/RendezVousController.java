package com.example.demo.Controller;

import com.example.demo.Dto.RendezVousDTO;
import com.example.demo.Service.RendezVousService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@SecurityRequirement(name = "bearerAuth")
@RestController
@Tag(name = "RendezVous", description = "Gestion des rendez-vous")
@RequestMapping("/rendezvous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @Autowired
    public RendezVousController(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }

    @Operation(summary = "Créer un rendez-vous")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rendez-vous créé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Ressource non trouvée")
    })
    @PostMapping
    public ResponseEntity<RendezVousDTO> createRendezVous(@RequestBody RendezVousDTO dto) {
        RendezVousDTO created = rendezVousService.prendreRendezVous(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupérer tous les rendez-vous")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée"),
            @ApiResponse(responseCode = "403", description = "Accès interdit")
    })
    @GetMapping
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVous() {
        return ResponseEntity.ok(rendezVousService.getAllRendezVous());
    }

    @Operation(summary = "Récupérer tous les rendez-vous")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée"),
            @ApiResponse(responseCode = "403", description = "Accès interdit")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RendezVousDTO> getById(@PathVariable Long id) {
        Optional<RendezVousDTO> dto = rendezVousService.getRendezVousById(id);
        return dto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Mettre à jour un rendez-vous")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rendez-vous mis à jour"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Rendez-vous non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RendezVousDTO> updateRendezVous(@PathVariable Long id, @RequestBody RendezVousDTO dto) {
        RendezVousDTO updated = rendezVousService.updateRendezVous(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Supprimer un rendez-vous")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rendez-vous supprimé"),
            @ApiResponse(responseCode = "403", description = "Accès interdit"),
            @ApiResponse(responseCode = "404", description = "Rendez-vous non trouvé")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
        return ResponseEntity.noContent().build();
    }
}
