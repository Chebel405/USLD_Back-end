package com.example.demo.Dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TraitementDTO {
    private Long id;
    private String nomMedicament;
    private Integer dosageMg;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer priseMatin;
    private Integer priseMidi;
    private Integer priseSoir;
    private Integer priseCoucher;
    private String observations;
    private Long patientId;
}
