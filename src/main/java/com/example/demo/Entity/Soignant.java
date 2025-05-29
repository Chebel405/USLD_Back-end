package com.example.demo.Entity;


import com.example.demo.Enums.TypeSoignant;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Soignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Enumerated(EnumType.STRING)
    private TypeSoignant type;

    @ManyToMany(mappedBy = "soignants")
    @JsonManagedReference
    private List<Patient> patients;
}
