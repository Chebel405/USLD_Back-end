package com.example.demo.Dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientAlzheimerDTO {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Integer numeroChambre;
    private String niveauAutonomie;
    private Boolean toiletteAssistee;
    private Boolean aideHabillage;
    private Boolean aideRepas;


    private String stadeMaladie;
    private Boolean suiviPsychologue;
    private String typePatient;

}
