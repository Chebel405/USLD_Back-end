package com.example.demo.Dto;

import com.example.demo.Enums.TypeSoignant;
import lombok.Data;

import java.util.List;

@Data
public class SoignantDTO {
    private Long id;
    private String nom;
    private String prenom;
    private TypeSoignant type;

    private List<Long> patientsIds;

}
