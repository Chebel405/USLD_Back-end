package com.example.demo.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PatientAlzheimer extends Patient {

    private String stadeMaladie;
    private Boolean suiviPsychologue;
}
