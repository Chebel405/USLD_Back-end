package com.example.demo.Repository;

import com.example.demo.Entity.PatientAlzheimer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientAlzheimerRepository extends JpaRepository<PatientAlzheimer, Long> {
    List<PatientAlzheimer> findByNomContainingIgnoreCase(String nom);
}
