package com.example.demo.Authentification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour gérer les opérations d'authentification.
 * Fournit deux routes : /auth/register et /auth/login.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentification", description = "Opérations liées à l'inscription et à la connexion")
public class AuthentificationController {

    private final AuthentificationService authService;

    public AuthentificationController(AuthentificationService authService) {
        this.authService = authService;
    }

    /**
     * Enregistre un nouvel utilisateur et retourne un JWT.
     */
    @Operation(summary = "Inscription d'un nouvel utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur inscrit avec succès, JWT retourné"),
            @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponseDTO> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données nécessaires pour l'inscription",
                    required = true,
                    content = @Content(schema = @Schema(implementation = RegisterRequestDTO.class))
            )
            @RequestBody RegisterRequestDTO request) {
        AuthentificationResponseDTO response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Authentifie un utilisateur avec email/mot de passe et retourne un JWT s’il est valide.
     */
    @Operation(summary = "Connexion d'un utilisateur existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentification réussie, JWT retourné"),
            @ApiResponse(responseCode = "403", description = "Accès interdit - identifiants invalides", content = @Content)
    })
    @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        AuthentificationResponseDTO response = authService.login(request);

        if (response == null || response.getToken() == null) {
            return ResponseEntity.status(403).body("Identifiants invalides");
        }

        return ResponseEntity.ok(response);
    }

}
