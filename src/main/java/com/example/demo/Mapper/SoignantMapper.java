package com.example.demo.Mapper;


import com.example.demo.Dto.SoignantDTO;
import com.example.demo.Entity.Soignant;
import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Mapper.PatientMapper;



import java.util.List;
import java.util.stream.Collectors;

public class SoignantMapper {

    /**
     * Convertit un objet Soignant (Entity) en SoignantDTO.
     * @param soignant l'entité Soignant à convertir
     * @return l'objet SoignantDTO correspondant
     */
    public static SoignantDTO toDTO(Soignant soignant) {
        if (soignant == null) return null;

        SoignantDTO dto = new SoignantDTO();
        dto.setId(soignant.getId());
        dto.setNom(soignant.getNom());
        dto.setPrenom(soignant.getPrenom());
        dto.setNumeroSoignant(soignant.getNumeroSoignant());
        dto.setType(soignant.getType());

        // Récupération des IDs des patients associés
        if (soignant.getPatients() != null) {
            List<Long> ids = soignant.getPatients()
                    .stream()
                    .map(Patient::getId)
                    .collect(Collectors.toList());
            dto.setPatientsIds(ids);

        }

        return dto;
    }

    /**
     * Convertit un SoignantDTO en Soignant (Entity).
     * Récupère les objets Patient associés via leurs IDs (patientsIds).
     *
     * @param dto le DTO à convertir
     * @param patientRepository le repository utilisé pour charger les patients par ID
     * @return l'entité Soignant correspondante
     */
    public static Soignant toEntity(SoignantDTO dto, PatientRepository patientRepository){
        if(dto == null) return null;

        Soignant soignant = new Soignant();
        soignant.setId(dto.getId());
        soignant.setNom(dto.getNom());
        soignant.setPrenom(dto.getPrenom());
        soignant.setNumeroSoignant(dto.getNumeroSoignant());
        soignant.setType(dto.getType());

        // Conversion des patientsIds en objets Patient
        if (dto.getPatientsIds() != null) {
            List<Patient> patients = dto.getPatientsIds().stream()
                    .map(id -> patientRepository.findById(id).orElse(null))
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
            soignant.setPatients(patients);
        }

        return soignant;
    }

}
