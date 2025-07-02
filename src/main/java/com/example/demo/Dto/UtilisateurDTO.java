package com.example.demo.Dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Représente un utilisateur enregistré dans le système (ex. : soignant).")
public class UtilisateurDTO {

    @Schema(description = "Identifiant unique de l'utilisateur", example = "1")
    private Long id;

    @Schema(description = "Adresse email de l'utilisateur", example = "paul.dupont@hopital.fr")
    private String email;

    // Le mot de passe n'est pas exposé ici pour des raisons de sécurité
    // private String motDePasse;
}
