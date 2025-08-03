// PatientAlzheimerService.java
package com.example.demo.Service;

import com.example.demo.Dto.PatientAlzheimerDTO;
import com.example.demo.Dto.PatientSansSoinDTO;
import com.example.demo.Dto.PatientUSLDDTO;

import java.time.LocalDate;
import java.util.List;

public interface PatientAlzheimerService {
    PatientAlzheimerDTO create(PatientAlzheimerDTO dto);
    List<PatientAlzheimerDTO> findAll();
    PatientAlzheimerDTO findById(Long id);
    PatientAlzheimerDTO update(Long id, PatientAlzheimerDTO dto);
    List<PatientAlzheimerDTO> findByNom(String nom);
    List<PatientAlzheimerDTO> findByPrenom(String prenom);
    List<PatientAlzheimerDTO> findByDateNaissance(LocalDate dateNaissance);
    List<PatientAlzheimerDTO> findByNumeroChambre(Integer numeroChambre);
    List<PatientAlzheimerDTO> findByNiveauAutonomie(String niveau);
    void delete(Long id);
}