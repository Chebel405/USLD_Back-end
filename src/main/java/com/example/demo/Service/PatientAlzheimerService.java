// PatientAlzheimerService.java
package com.example.demo.Service;

import com.example.demo.Dto.PatientAlzheimerDTO;
import java.util.List;

public interface PatientAlzheimerService {
    PatientAlzheimerDTO create(PatientAlzheimerDTO dto);
    List<PatientAlzheimerDTO> findAll();
    PatientAlzheimerDTO findById(Long id);
    PatientAlzheimerDTO update(Long id, PatientAlzheimerDTO dto);
    void delete(Long id);
}