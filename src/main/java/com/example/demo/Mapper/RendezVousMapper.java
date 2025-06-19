package com.example.demo.Mapper;

import com.example.demo.Dto.RendezVousDTO;
import com.example.demo.Entity.RendezVous;
import com.example.demo.Entity.Patient;
import com.example.demo.Entity.Soignant;
import com.example.demo.Entity.Soin;

import com.example.demo.Repository.RendezVousRepository;
public class RendezVousMapper {

    public static RendezVousDTO toDTO(RendezVous rendezVous) {
        if (rendezVous == null) return null;

        RendezVousDTO dto = new RendezVousDTO();
        dto.setId(rendezVous.getId());
        dto.setDateHeure(rendezVous.getDateHeure());
        dto.setMotif(rendezVous.getMotif());
        dto.setPatientId(rendezVous.getPatient().getId());
        dto.setSoignantId(rendezVous.getSoignant().getId());

        if (rendezVous.getSoin() != null) {
            dto.setSoinId(rendezVous.getSoin().getId());
        }
        return dto;
    }

    public static RendezVous toEntity(RendezVousDTO dto, Patient patient, Soignant soignant, Soin soin) {
        if (dto == null) return null;

        RendezVous entity = new RendezVous();
        entity.setId(dto.getId());
        entity.setDateHeure(dto.getDateHeure());
        entity.setMotif(dto.getMotif());
        entity.setPatient(patient);
        entity.setSoignant(soignant);
        entity.setSoin(soin);
        return entity;
    }
}
