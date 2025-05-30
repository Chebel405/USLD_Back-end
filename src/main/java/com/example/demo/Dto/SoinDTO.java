package com.example.demo.Dto;

import com.example.demo.Enums.TypeSoins;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SoinDTO {
    private Long id;
    private LocalDate date;
    private TypeSoins type;
    private String description;
    private Long patientId;
    private Long soignantId;
}
