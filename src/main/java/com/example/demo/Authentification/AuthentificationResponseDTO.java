package com.example.demo.Authentification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Schema(description = "Réponse renvoyée après une authentification réussie.")
@AllArgsConstructor
public class AuthentificationResponseDTO {

    @Schema(description = "Jeton JWT permettant d'accéder aux ressources sécurisées", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}
