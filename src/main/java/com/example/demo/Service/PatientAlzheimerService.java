// PatientAlzheimerService.java
package com.example.demo.Service;

import com.example.demo.Dto.PatientAlzheimerDTO;
import com.example.demo.Dto.PatientSansSoinDTO;

import java.util.List;

public interface PatientAlzheimerService {
    PatientAlzheimerDTO create(PatientAlzheimerDTO dto);
    List<PatientAlzheimerDTO> findAll();
    PatientAlzheimerDTO findById(Long id);
    PatientAlzheimerDTO update(Long id, PatientAlzheimerDTO dto);
    List<PatientAlzheimerDTO> findByNom(String nom);
    void delete(Long id);
}