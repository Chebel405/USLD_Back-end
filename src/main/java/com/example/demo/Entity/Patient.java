package com.example.demo.Entity;

import com.example.demo.Enums.TypePatient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Entity
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
    @ManyToMany
    @JoinTable(
            name = "patient_soignant",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "soignant_id")
    )
    @JsonBackReference
    private List<Soignant> soignants;

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVousList;



}
