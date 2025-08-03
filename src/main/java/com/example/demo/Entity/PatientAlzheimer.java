package com.example.demo.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//PatientAlzheimer hérite de la class Patient
public class PatientAlzheimer extends Patient {

    private String stadeMaladie;
    private Boolean suiviPsychologue;
}
