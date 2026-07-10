package com.example.demo.Mapper;

import com.example.demo.Dto.TraitementDTO;
import com.example.demo.Entity.Traitement;

public class TraitementMapper {

    public static TraitementDTO toDTO(Traitement traitement){
        if (traitement == null) return null;

        TraitementDTO dto = new TraitementDTO();
        dto.setId(traitement.getId());
        dto.setNomMedicament(traitement.getNomMedicament());
        dto.setDosageMg(traitement.getDosageMg());
        dto.setDateDebut(traitement.getDateDebut());
        dto.setDateFin(traitement.getDateFin());
        dto.setPriseMatin(traitement.getPriseMatin());
        dto.setPriseMidi(traitement.getPriseMidi());
        dto.setPriseSoir(traitement.getPriseSoir());
        dto.setPriseCoucher(traitement.getPriseCoucher());
        dto.setObservations(traitement.getObservations());

        if(traitement.getPatient() != null){
            dto.setPatientId(traitement.getPatient().getId());
        }
        return dto;

    }

    public static Traitement toEntity(TraitementDTO dto) {

        if (dto == null) {
            return null;
        }

        Traitement traitement = new Traitement();

        traitement.setId(dto.getId());
        traitement.setNomMedicament(dto.getNomMedicament());
        traitement.setDosageMg(dto.getDosageMg());
        traitement.setDateDebut(dto.getDateDebut());
        traitement.setDateFin(dto.getDateFin());
        traitement.setPriseMatin(dto.getPriseMatin());
        traitement.setPriseMidi(dto.getPriseMidi());
        traitement.setPriseSoir(dto.getPriseSoir());
        traitement.setPriseCoucher(dto.getPriseCoucher());
        traitement.setObservations(dto.getObservations());

        return traitement;
    }
}
