package com.example.demo;

import com.example.demo.Entity.Patient;
import com.example.demo.Entity.Soignant;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.SoignantRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final PatientRepository patientRepository;
    private final SoignantRepository soignantRepository;

    public TestController(PatientRepository patientRepository, SoignantRepository soignantRepository) {
        this.patientRepository = patientRepository;
        this.soignantRepository = soignantRepository;
    }

    @PostMapping("/associer")
    public String associerPatientEtSoignant() {
        Soignant soignant = soignantRepository.findById(1L).orElse(null);
        Patient patient = patientRepository.findById(1L).orElse(null);

        if (soignant != null && patient != null) {
            patient.getSoignants().add(soignant);
            patientRepository.save(patient);
            return "Association enregistrée ✅";
        } else {
            return "Patient ou soignant introuvable ❌";
        }
    }
}
