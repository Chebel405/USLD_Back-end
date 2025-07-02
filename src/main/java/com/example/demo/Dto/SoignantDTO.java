package com.example.demo.Dto;

import com.example.demo.Enums.TypeSoignant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Représente un Soignant (médecin, aide-soignant, etc.).")
public class SoignantDTO {

    @Schema(description = "Identifiant unique du soignant", example = "1")
    private Long id;

    @Schema(description = "Nom du soignant", example = "Dupont")
    private String nom;

    @Schema(description = "Prénom du soignant", example = "Claire")
    private String prenom;

    @Schema(description = "Type du soignant (MEDECIN, INFIRMIER, AIDE_SOIGNANT, etc.)", example = "MEDECIN")
    private TypeSoignant type;


    @Schema(description = "Liste des IDs des patients pris en charge")
    private List<Long> patientsIds;

    @Schema(description = "Liste des rendez-vous associés au soignant")
    private List<RendezVousDTO> rendezVousDTOList;


}
