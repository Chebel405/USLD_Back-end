package com.example.demo.Authentification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO utilisé pour l'authentification d'un utilisateur existant.
 */
@Data
@Schema(description = "Données nécessaires pour authentifier un utilisateur.")
public class LoginRequestDTO {

    @Schema(description = "Adresse email de l'utilisateur", example = "soignant1@hopital.fr", required = true)
    private String email;

    @Schema(description = "Mot de passe de l'utilisateur", example = "motDePasseSécurisé123", required = true)
    private String motDePasse;
}
