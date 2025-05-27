package com.example.demo.Mapper;

import com.example.demo.Dto.SoignantDTO;
import com.example.demo.Entity.Soignant;
import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SoignantMapper {
    // Entity => DTO
    public static SoignantDTO toDTO(Soignant soignant) {
        if (soignant == null) return null;

        SoignantDTO dto = new SoignantDTO();
        dto.setId(soignant.getId());
        dto.setNom(soignant.getNom());
        dto.setPrenom(soignant.getPrenom());
        dto.setType(soignant.getType());

        // ⬇️ Ajout du mapping de la liste des patients
        if (soignant.getPatients() != null) {
            List<Long> ids = soignant.getPatients()
                    .stream()
                    .map(Patient::getId)
                    .collect(Collectors.toList());
            dto.setPatientsIds(ids);
        }
        return dto;
    }

    // DTO => Entity
    public static Soignant toEntity(SoignantDTO dto, PatientRepository patientRepository){
        if(dto == null) return null;

        Soignant soignant = new Soignant();
        soignant.setId(dto.getId());
        soignant.setNom(dto.getNom());
        soignant.setPrenom(dto.getPrenom());
        soignant.setType(dto.getType());

        // ⬇️ Ajout de la conversion des IDs en objets Soignant
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
