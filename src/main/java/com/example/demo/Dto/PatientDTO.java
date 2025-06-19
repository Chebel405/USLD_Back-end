package com.example.demo.Dto;

import com.example.demo.Enums.TypePatient;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

/**
 * Représente un Patient côté transfert de données (DTO).
 * Utilisé pour envoyer ou recevoir des données via l'API, sans exposer l'entité complète.
 */
public class PatientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private TypePatient type;

    private Integer numeroChambre;
    private String niveauAutonomie;

    private Boolean toiletteAssistee;
    private Boolean aideHabillage;
    private Boolean aideRepas;

    // Liste des IDs des soignants liés à ce patient (permet d'éviter les cycles d'objet complets)
    private List<Long> soignantsIds;

    // Liste des soignants complets associés (optionnel, selon les besoins du front)
    private List<SoignantDTO> soignants;

    private List<RendezVousDTO> rendezVousDTOList;
}
