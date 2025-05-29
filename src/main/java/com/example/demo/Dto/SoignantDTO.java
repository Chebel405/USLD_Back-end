package com.example.demo.Dto;

import com.example.demo.Enums.TypeSoignant;
import lombok.Data;

import java.util.List;

@Data

/**
 * Représente un Soignant côté transfert de données (DTO).
 * Utilisé pour envoyer ou recevoir des données via l'API, sans exposer l'entité complète.
 */
public class SoignantDTO {
    private Long id;
    private String nom;
    private String prenom;
    private TypeSoignant type;

    private List<Long> patientsIds;


}
