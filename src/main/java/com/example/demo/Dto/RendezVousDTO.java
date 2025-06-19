package com.example.demo.Dto;

import lombok.Data;


import java.time.LocalDateTime;


@Data
public class RendezVousDTO {
    private Long id;
    private LocalDateTime dateHeure;
    private String motif;

    private Long patientId;     // ID du patient concerné
    private Long soignantId;    // ID du soignant (médecin, kiné, etc.)
    private Long soinId;           // ID du soin associé (facultatif)

}
