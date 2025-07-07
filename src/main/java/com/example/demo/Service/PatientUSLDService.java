package com.example.demo.Service;

import com.example.demo.Dto.PatientUSLDDTO;
import java.util.List;

public interface PatientUSLDService {
    PatientUSLDDTO create(PatientUSLDDTO dto);
    List<PatientUSLDDTO> findAll();
    PatientUSLDDTO findById(Long id);
    PatientUSLDDTO update(Long id, PatientUSLDDTO dto);
    void delete(Long id);
}