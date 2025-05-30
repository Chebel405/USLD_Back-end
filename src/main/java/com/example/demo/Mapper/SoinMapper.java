package com.example.demo.Mapper;


import com.example.demo.Dto.SoinDTO;
import com.example.demo.Entity.Soin;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.SoignantRepository;


public class SoinMapper {

    public static SoinDTO toDTO(Soin soin) {
        if (soin == null) return null;

        SoinDTO dto = new SoinDTO();
        dto.setId(soin.getId());
        dto.setDate(soin.getDate());
        dto.setType(soin.getType());
        dto.setDescription(soin.getDescription());

       if (soin.getPatient() != null)
           dto.setPatientId(soin.getPatient().getId());

       if (soin.getSoignant() != null)
           dto.setSoignantId(soin.getSoignant().getId());

        return dto;
    }

    public static Soin toEntity(SoinDTO dto, PatientRepository patientRepository, SoignantRepository soignantRepository) {
        if (dto == null) return null;

        Soin soin = new Soin();
        soin.setId(dto.getId());
        soin.setDate(dto.getDate());
        soin.setType(dto.getType());
        soin.setDescription(dto.getDescription());

        if (dto.getPatientId() != null) {
            patientRepository.findById(dto.getPatientId()).ifPresent(soin::setPatient);
        }

        if (dto.getSoignantId() != null) {
            soignantRepository.findById(dto.getSoignantId()).ifPresent(soin::setSoignant);
        }

        return soin;
    }
    
}
