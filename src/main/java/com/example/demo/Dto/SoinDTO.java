package com.example.demo.Dto;

import com.example.demo.Enums.TypeSoins;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "Représente un soin médical ou paramédical dispensé à un patient.")
public class SoinDTO {

    @Schema(description = "Identifiant unique du soin", example = "5")
    private Long id;

    @Schema(description = "Date à laquelle le soin a été dispensé", example = "2025-06-15")
    private LocalDate date;

    @Schema(description = "Type de soin (KINÉ, INFIRMIER, etc.)", example = "KINE")
    private TypeSoins type;

    @Schema(description = "Description détaillée du soin", example = "Séance de rééducation post-opératoire")
    private String description;


    @Schema(description = "ID du patient ayant reçu le soin", example = "2")
    private Long patientId;

    @Schema(description = "ID du soignant ayant réalisé le soin", example = "1")
    private Long soignantId;
}
