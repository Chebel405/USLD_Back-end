package com.example.demo.Mapper;

import com.example.demo.Dto.PatientDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Entity.Soignant;
import com.example.demo.Repository.SoignantRepository;


import java.util.List;
import java.util.stream.Collectors;

public class PatientMapper {

    // Entity => DTO
    public static PatientDTO toDTO(Patient patient){
        if(patient == null) return null;

        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setDateNaissance(patient.getDateNaissance());
        dto.setType(patient.getType());
        dto.setNumeroChambre(patient.getNumeroChambre());
        dto.setNiveauAutonomie(patient.getNiveauAutonomie());
        dto.setToiletteAssistee(patient.getToiletteAssistee());
        dto.setAideHabillage(patient.getAideHabillage());
        dto.setAideRepas(patient.getAideRepas());

        // ⬇️ Ajout du mapping de la liste des soignants
        if (patient.getSoignants() != null) {
            List<Long> ids = patient.getSoignants()
                    .stream()
                    .map(Soignant::getId)
                    .collect(Collectors.toList());
            dto.setSoignantsIds(ids);
        }

        return dto;
    }

    // DTO => Entity
    public static Patient toEntity(PatientDTO dto, SoignantRepository soignantRepository){
        if(dto == null) return null;

        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(dto.getDateNaissance());
        patient.setType(dto.getType());
        patient.setNumeroChambre(dto.getNumeroChambre());
        patient.setNiveauAutonomie(dto.getNiveauAutonomie());
        patient.setToiletteAssistee(dto.getToiletteAssistee());
        patient.setAideHabillage(dto.getAideHabillage());
        patient.setAideRepas(dto.getAideRepas());

        // ⬇️ Ajout de la conversion des IDs en objets Soignant
        if (dto.getSoignantsIds() != null) {
            List<Soignant> soignants = dto.getSoignantsIds().stream()
                    .map(id -> soignantRepository.findById(id).orElse(null))
                    .filter(s -> s != null)
                    .collect(Collectors.toList());
            patient.setSoignants(soignants);
        }

        return patient;
    }
}
