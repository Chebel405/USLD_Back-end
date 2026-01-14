package com.example.demo.Repository;

import com.example.demo.Entity.PatientUSLD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository JPA dédié aux patients de type USLD.
 *
 * 🔍 Fournit des méthodes spécifiques à PatientUSLD
 * – ex : findByNumeroChambre, niveauAutonomie, dateNaissance, etc.
 * Ce repo est utilisé sur des pages ou endpoints réservés au type USLD.
 */
public interface PatientUSLDRepository extends JpaRepository<PatientUSLD, Long> {
    List<PatientUSLD> findByNomContainingIgnoreCase(String nom);

    List<PatientUSLD> findByPrenomContainingIgnoreCase(String prenom);

    List<PatientUSLD> findByDateNaissance(LocalDate dateNaissance);

    List<PatientUSLD> findByNumeroChambre(String numeroChambre);

    List<PatientUSLD> findByNiveauAutonomieContainingIgnoreCase(String niveauAutonomie);
}
