package com.example.demo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.time.LocalDateTime;


@Data
@Schema(description = "Représente un rendez-vous entre un patient et un soignant.")
public class RendezVousDTO {

    @Schema(description = "Identifiant unique du rendez-vous", example = "1")
    private Long id;

    @Schema(description = "Date et heure du rendez-vous", example = "2025-07-01T10:30:00")
    private LocalDateTime dateHeure;

    @Schema(description = "Motif du rendez-vous", example = "Consultation annuelle")
    private String motif;


    @Schema(description = "ID du patient concerné", example = "3")
    private Long patientId;

    @Schema(description = "ID du soignant responsable", example = "1")
    private Long soignantId;

    @Schema(description = "ID du soin lié au rendez-vous (facultatif)", example = "2")
    private Long soinId;

}
