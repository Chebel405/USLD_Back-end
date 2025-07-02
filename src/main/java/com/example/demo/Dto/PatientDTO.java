package com.example.demo.Dto;

import com.example.demo.Enums.TypePatient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "Représente un Patient (DTO) utilisé pour la communication via l'API")

public class PatientDTO {

    @Schema(description = "Identifiant unique du patient", example = "1")
    private Long id;

    @Schema(description = "Nom du patient", example = "Martin")
    private String nom;

    @Schema(description = "Prénom du patient", example = "Claire")
    private String prenom;

    @Schema(description = "Date de naissance du patient", example = "1950-04-12")
    private LocalDate dateNaissance;

    @Schema(description = "Type de patient (AVEC_SOINS, SANS_SOINS)", example = "AVEC_SOINS")
    private TypePatient type;

    @Schema(description = "Numéro de chambre du patient", example = "203")
    private Integer numeroChambre;

    @Schema(description = "Niveau d'autonomie du patient", example = "Faible")
    private String niveauAutonomie;

    @Schema(description = "Indique si le patient a besoin d’aide pour la toilette", example = "true")
    private Boolean toiletteAssistee;

    @Schema(description = "Indique si le patient a besoin d’aide pour s’habiller", example = "false")
    private Boolean aideHabillage;

    @Schema(description = "Indique si le patient a besoin d’aide pour les repas", example = "true")
    private Boolean aideRepas;


    @Schema(description = "Liste des identifiants des soignants associés au patient")
    private List<Long> soignantsIds;

    @Schema(description = "Liste complète des soignants associés (enrichissement optionnel)")
    private List<SoignantDTO> soignants;

    @Schema(description = "Liste des rendez-vous du patient")
    private List<RendezVousDTO> rendezVousDTOList;
}
