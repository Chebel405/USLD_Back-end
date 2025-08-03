// PatientSansSoinService.java
package com.example.demo.Service;

import com.example.demo.Dto.PatientSansSoinDTO;
import com.example.demo.Dto.PatientUSLDDTO;

import java.time.LocalDate;
import java.util.List;

public interface PatientSansSoinService {
    PatientSansSoinDTO create(PatientSansSoinDTO dto);
    List<PatientSansSoinDTO> findAll();
    PatientSansSoinDTO findById(Long id);
    PatientSansSoinDTO update(Long id, PatientSansSoinDTO dto);
    List<PatientSansSoinDTO> findByNom(String nom);
    List<PatientSansSoinDTO> findByPrenom(String prenom);
    List<PatientSansSoinDTO> findByDateNaissance(LocalDate dateNaissance);
    List<PatientSansSoinDTO> findByNumeroChambre(Integer numeroChambre);
    List<PatientSansSoinDTO> findByNiveauAutonomie(String niveau);
    void delete(Long id);
}
