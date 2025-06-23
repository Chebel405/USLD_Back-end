package com.example.demo.Dto;


import lombok.Data;

@Data
/**
 * Représente un Utilisateur côté transfert de données (DTO).
 * Utilisé pour envoyer ou recevoir des données via l'API, sans exposer l'entité complète.
 */
public class UtilisateurDTO {
    private Long id;
    private String email;
   /* private String motDePasse;*/
}
