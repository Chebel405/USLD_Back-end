package com.example.demo.Service;

import com.example.demo.Dto.PatientDTO;
import org.springframework.context.annotation.ComponentScan;


import java.util.List;
import java.util.Optional;


public interface PatientService {

    PatientDTO createPatient(PatientDTO patientDTO);

    List<PatientDTO>findAll();

    Optional<PatientDTO>findById(Long id);

    PatientDTO save(PatientDTO patientDTO);

    void deletePatient(Long id);

    PatientDTO updatePatient(Long id, PatientDTO patientDTO);
}
