package com.example.demo.Dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientAlzheimerDTO {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    private String stadeMaladie;
    private Boolean suiviPsychologue;
}
