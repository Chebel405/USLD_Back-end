package com.example.demo.Entity;

import com.example.demo.Enums.TypePatient;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    @Enumerated(EnumType.STRING)
    private TypePatient type;

    private Integer numeroChambre;
    private String niveauAutonomie;

    private Boolean toiletteAssistee;
    private Boolean aideHabillage;
    private Boolean aideRepas;
}
