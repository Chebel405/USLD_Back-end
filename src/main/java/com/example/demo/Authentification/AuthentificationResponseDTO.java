package com.example.demo.Authentification;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Représente la réponse renvoyée après une authentification ou un enregistrement réussi.
 * Contient uniquement le token JWT généré pour l'utilisateur.
 */
@Data
@AllArgsConstructor
public class AuthentificationResponseDTO {
    private String token;
}
