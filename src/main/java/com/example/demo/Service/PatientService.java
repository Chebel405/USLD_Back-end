package com.example.demo.Service;

import com.example.demo.Dto.PatientDTO;
import com.example.demo.Entity.Patient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@ComponentScan
public interface PatientService {

    PatientDTO createPatient(PatientDTO patientDTO);

    List<PatientDTO>findAll();

    Optional<PatientDTO>findById(Long id);

    PatientDTO save(PatientDTO patientDTO);

    void deletePatient(Long id);

    PatientDTO updatePatient(Long id, PatientDTO patientDTO);
}
