package com.example.demo.Repository;

import com.example.demo.Entity.PatientAlzheimer;
import com.example.demo.Entity.PatientUSLD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientAlzheimerRepository extends JpaRepository<PatientAlzheimer, Long> {
    List<PatientAlzheimer> findByNomContainingIgnoreCase(String nom);
    List<PatientAlzheimer> findByPrenomContainingIgnoreCase(String prenom);

    List<PatientAlzheimer> findByDateNaissance(LocalDate dateNaissance);

    List<PatientAlzheimer> findByNumeroChambre(Integer numeroChambre);

    List<PatientAlzheimer> findByNiveauAutonomieContainingIgnoreCase(String niveauAutonomie);
}
