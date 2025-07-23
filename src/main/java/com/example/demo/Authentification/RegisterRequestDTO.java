package com.example.demo.Authentification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO utilisé pour l'enregistrement d'un nouvel utilisateur.
 */
@Data
@Schema(description = "Données nécessaires pour enregistrer un nouvel utilisateur.")
public class RegisterRequestDTO {

    @Schema(description = "Nom de l'utilisateur", example = "Durand", required = true)
    private String nom;

    @Schema(description = "Adresse email de l'utilisateur", example = "soignant1@hopital.fr", required = true)
    private String email;

    @Schema(description = "Mot de passe de l'utilisateur", example = "motDePasseSécurisé123", required = true)
    private String motDePasse;
}
