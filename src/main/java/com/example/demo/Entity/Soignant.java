package com.example.demo.Entity;


import com.example.demo.Enums.TypeSoignant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
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
    private Set<Patient> patients;
}
