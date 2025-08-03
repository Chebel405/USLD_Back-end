package com.example.demo.Repository;

import com.example.demo.Entity.PatientAlzheimer;
import com.example.demo.Entity.PatientSansSoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientSansSoinRepository extends JpaRepository<PatientSansSoin, Long> {
    List<PatientSansSoin> findByNomContainingIgnoreCase(String nom);
    List<PatientSansSoin> findByPrenomContainingIgnoreCase(String prenom);

    List<PatientSansSoin> findByDateNaissance(LocalDate dateNaissance);

    List<PatientSansSoin> findByNumeroChambre(Integer numeroChambre);

    List<PatientSansSoin> findByNiveauAutonomieContainingIgnoreCase(String niveauAutonomie);
}
