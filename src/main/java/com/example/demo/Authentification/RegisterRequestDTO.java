package com.example.demo.Authentification;

import lombok.Data;

/**
 * DTO pour l'inscription d'un utilisateur.
 * Contient les champs nécessaires pour enregistrer un nouvel utilisateur.
 */
@Data
public class RegisterRequestDTO {
    private String email;
    private String motDePasse;
}
