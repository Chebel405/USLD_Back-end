package com.example.demo.Repository;

import com.example.demo.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository JPA pour tous les types de patients (USLD, Alzheimer, Sans Soin…).
 *
 * ✅ Peut être utilisé :
 * - soit avec des méthodes findBy... classiques
 * - soit avec des Specifications via JpaSpecificationExecutor
 *
 * 👉 Pour le moment, la recherche avancée utilise Specification,
 * mais ces méthodes sont conservées pour référence ou usage futur.
 */
public interface PatientRepository
        extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    List<Patient> findByNomContainingIgnoreCase(String nom);

    List<Patient> findByPrenomContainingIgnoreCase(String prenom);

    List<Patient> findByDateNaissance(LocalDate dateNaissance);

    List<Patient> findByNumeroChambre(String numeroChambre);

    List<Patient> findByNiveauAutonomieContainingIgnoreCase(String niveauAutonomie);

}
