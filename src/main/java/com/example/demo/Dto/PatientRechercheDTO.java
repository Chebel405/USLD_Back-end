package com.example.demo.Dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PatientRechercheDTO {

        private Long id;
        private String nom;
        private String prenom;
        private LocalDate dateNaissance;
        private String numeroChambre;
        private String niveauAutonomie;


        private String typePatient; // USLD / ALZHEIMER / SANS_SOIN
    }


