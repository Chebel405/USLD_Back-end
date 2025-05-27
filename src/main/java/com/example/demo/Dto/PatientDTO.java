package com.example.demo.Dto;

import com.example.demo.Enums.TypePatient;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
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

    private List<Long> soignantsIds;
    private List<SoignantDTO> soignants;
}
