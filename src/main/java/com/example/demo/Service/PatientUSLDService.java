package com.example.demo.Service;

import com.example.demo.Dto.PatientUSLDDTO;

import java.time.LocalDate;
import java.util.List;

public interface PatientUSLDService {
    PatientUSLDDTO create(PatientUSLDDTO dto);
    List<PatientUSLDDTO> findAll();
    PatientUSLDDTO findById(Long id);
    PatientUSLDDTO update(Long id, PatientUSLDDTO dto);
    List<PatientUSLDDTO> findByNom(String nom);
    List<PatientUSLDDTO> findByPrenom(String prenom);
    List<PatientUSLDDTO> findByDateNaissance(LocalDate dateNaissance);
    List<PatientUSLDDTO> findByNumeroChambre(Integer numeroChambre);
    List<PatientUSLDDTO> findByNiveauAutonomie(String niveau);
    void delete(Long id);
}