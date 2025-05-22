package com.example.demo.ServiceImpl;

import com.example.demo.Entity.Patient;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(patientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isPresent()) {
            Patient existePatient = optionalPatient.get();
            existePatient.setNom(patient.getNom());
            existePatient.setPrenom(patient.getPrenom());
            existePatient.setDateNaissance(patient.getDateNaissance());
            existePatient.setType(patient.getType());
            existePatient.setNumeroChambre(patient.getNumeroChambre());
            existePatient.setNiveauAutonomie(patient.getNiveauAutonomie());
            existePatient.setToiletteAssistee(patient.getToiletteAssistee());
            existePatient.setAideHabillage(patient.getAideHabillage());
            existePatient.setAideRepas(patient.getAideRepas());

            return patientRepository.save(existePatient);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient non trouvé par ID: " + id);

        }
    }
}
