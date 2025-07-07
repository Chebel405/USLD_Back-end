package com.example.demo.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PatientUSLD extends Patient {

    private Integer numeroChambre;
    private String niveauAutonomie;

    private Boolean toiletteAssistee;
    private Boolean aideHabillage;
    private Boolean aideRepas;
}
