package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_patient")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PatientUSLD.class, name = "patientUSLD"),
        @JsonSubTypes.Type(value = PatientAlzheimer.class, name = "patientAlzheimer"),
        @JsonSubTypes.Type(value = PatientSansSoin.class, name = "patientSansSoin")
})
@Data
@NoArgsConstructor

public abstract class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    private LocalDate dateNaissance;

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
