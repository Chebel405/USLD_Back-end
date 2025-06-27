package com.example.demo.Authentification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour gérer les opérations d'authentification.
 * Fournit deux routes : /auth/register et /auth/login.
 */

@RestController
@RequestMapping("/auth")
public class AuthentificationController {

    private final AuthentificationService authService;

    public AuthentificationController(AuthentificationService authService) {
        this.authService = authService;
    }

    /**
     * Enregistre un nouvel utilisateur et retourne un JWT.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        AuthentificationResponseDTO response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Authentifie un utilisateur avec email/mot de passe et retourne un JWT s’il est valide.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthentificationResponseDTO> login(@RequestBody LoginRequestDTO request) {
        AuthentificationResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
