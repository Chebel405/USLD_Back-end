// PatientSansSoinService.java
package com.example.demo.Service;

import com.example.demo.Dto.PatientSansSoinDTO;
import java.util.List;

public interface PatientSansSoinService {
    PatientSansSoinDTO create(PatientSansSoinDTO dto);
    List<PatientSansSoinDTO> findAll();
    PatientSansSoinDTO findById(Long id);
    PatientSansSoinDTO update(Long id, PatientSansSoinDTO dto);
    List<PatientSansSoinDTO> findByNom(String nom);
    void delete(Long id);
}
