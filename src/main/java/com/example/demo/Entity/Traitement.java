package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Traitement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
