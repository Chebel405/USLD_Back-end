package com.example.demo.Repository;

import com.example.demo.Entity.Patient;
import com.example.demo.Entity.PatientUSLD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository JPA pour tous types de patients (USLD, Alzheimer, Sans Soin…).
 *
 * ✅ Contient des méthodes génériques utilisables pour tous les Patient concret
 * (recherches sur champ commun comme nom, prénom, etc.).
 * Utilisé notamment pour des pages de recherche globales sans distinction de type.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContainingIgnoreCase(String nom);


}
