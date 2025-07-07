package com.example.demo.Mapper;

import com.example.demo.Dto.*;
import com.example.demo.Entity.*;


public class PatientMapper {

    public static PatientUSLDDTO toDTO(PatientUSLD patient) {
        if (patient == null) return null;

        PatientUSLDDTO dto = new PatientUSLDDTO();
        dto.setId(patient.getId());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setDateNaissance(patient.getDateNaissance());
        dto.setNumeroChambre(patient.getNumeroChambre());
        dto.setNiveauAutonomie(patient.getNiveauAutonomie());
        dto.setToiletteAssistee(patient.getToiletteAssistee());
        dto.setAideHabillage(patient.getAideHabillage());
        dto.setAideRepas(patient.getAideRepas());
        return dto;
    }

    public static PatientUSLD toEntity(PatientUSLDDTO dto) {
        if (dto == null) return null;

        PatientUSLD patient = new PatientUSLD();
        patient.setId(dto.getId());
        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(dto.getDateNaissance());
        patient.setNumeroChambre(dto.getNumeroChambre());
        patient.setNiveauAutonomie(dto.getNiveauAutonomie());
        patient.setToiletteAssistee(dto.getToiletteAssistee());
        patient.setAideHabillage(dto.getAideHabillage());
        patient.setAideRepas(dto.getAideRepas());
        return patient;
    }

    // === Alzheimer ===
    public static PatientAlzheimerDTO toDTO(PatientAlzheimer patient) {
        if (patient == null) return null;

        PatientAlzheimerDTO dto = new PatientAlzheimerDTO();
        dto.setId(patient.getId());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setDateNaissance(patient.getDateNaissance());
        dto.setStadeMaladie(patient.getStadeMaladie());
        dto.setSuiviPsychologue(patient.getSuiviPsychologue());
        return dto;
    }

    public static PatientAlzheimer toEntity(PatientAlzheimerDTO dto) {
        if (dto == null) return null;

        PatientAlzheimer patient = new PatientAlzheimer();
        patient.setId(dto.getId());
        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(dto.getDateNaissance());
        patient.setStadeMaladie(dto.getStadeMaladie());
        patient.setSuiviPsychologue(dto.getSuiviPsychologue());
        return patient;
    }

    // === Sans Soin ===
    public static PatientSansSoinDTO toDTO(PatientSansSoin patient) {
        if (patient == null) return null;

        PatientSansSoinDTO dto = new PatientSansSoinDTO();
        dto.setId(patient.getId());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setDateNaissance(patient.getDateNaissance());
        return dto;
    }

    public static PatientSansSoin toEntity(PatientSansSoinDTO dto) {
        if (dto == null) return null;

        PatientSansSoin patient = new PatientSansSoin();
        patient.setId(dto.getId());
        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(dto.getDateNaissance());
        return patient;
    }
}
