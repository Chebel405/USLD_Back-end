package com.example.demo.Dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientSansSoinDTO {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String typePatient;

}
