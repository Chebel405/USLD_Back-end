package com.example.demo.Service;

import com.example.demo.Entity.Patient;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;

@ComponentScan
public interface PatientService {

    Patient createPatient(Patient patient);

    List<Patient>findAll();

    Optional<Patient>findById(Long id);

    Patient save(Patient patient);

    void deletePatient(Long id);

    Patient updatePatient(Long id, Patient patient);
}
