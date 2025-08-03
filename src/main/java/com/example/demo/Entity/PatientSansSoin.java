package com.example.demo.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//PatientSansSoin hérite de la class Patient
public class PatientSansSoin extends Patient {
    // Aucun champ spécifique pour ce type
}
