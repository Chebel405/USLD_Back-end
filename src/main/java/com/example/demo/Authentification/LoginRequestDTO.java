package com.example.demo.Authentification;

import lombok.Data;

/**
 * DTO pour la connexion d'un utilisateur.
 * Utilisé lors de l'envoi des identifiants pour générer un token JWT.
 */
@Data
public class LoginRequestDTO {
    private String email;
    private String motDePasse;
}
