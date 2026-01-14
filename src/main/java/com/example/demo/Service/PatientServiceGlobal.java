package com.example.demo.Service;

import com.example.demo.Dto.PatientRechercheDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Entity.PatientSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.PatientUSLD;
import com.example.demo.Entity.PatientAlzheimer;
import com.example.demo.Entity.PatientSansSoin;


import java.util.List;

@Service
public class PatientServiceGlobal {

    private final PatientRepository patientRepository;

    public PatientServiceGlobal(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientRechercheDTO> search(PatientRechercheDTO request) {

        Specification<Patient> spec = Specification
                .where(PatientSpecification.contientIgnoreCase("nom", request.getNom()))
                .and(PatientSpecification.contientIgnoreCase("prenom", request.getPrenom()))
                .and(PatientSpecification.contientIgnoreCase("numeroChambre", request.getNumeroChambre()))
                .and(PatientSpecification.contientIgnoreCase("niveauAutonomie", request.getNiveauAutonomie()))
                .and(PatientSpecification.egal("dateNaissance", request.getDateNaissance()))
                .and(PatientSpecification.typePatient(request.getTypePatient()));

        return patientRepository.findAll(spec)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private PatientRechercheDTO toDto(Patient p) {
        PatientRechercheDTO dto = new PatientRechercheDTO();
        dto.setId(p.getId());
        dto.setNom(p.getNom());
        dto.setPrenom(p.getPrenom());
        dto.setDateNaissance(p.getDateNaissance());
        dto.setNumeroChambre(p.getNumeroChambre());
        dto.setNiveauAutonomie(p.getNiveauAutonomie());
        //déterminer le type concret du patient (héritage)
        if (p instanceof PatientUSLD) dto.setTypePatient("USLD");
        else if (p instanceof PatientAlzheimer) dto.setTypePatient("ALZHEIMER");
        else if (p instanceof PatientSansSoin) dto.setTypePatient("SANS_SOIN");
        else dto.setTypePatient("INCONNU");

        return dto;
    }
}
