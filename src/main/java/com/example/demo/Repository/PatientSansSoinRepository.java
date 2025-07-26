package com.example.demo.Repository;

import com.example.demo.Entity.PatientSansSoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientSansSoinRepository extends JpaRepository<PatientSansSoin, Long> {
    List<PatientSansSoin> findByNomContainingIgnoreCase(String nom);
}
