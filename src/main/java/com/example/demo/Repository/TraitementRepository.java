package com.example.demo.Repository;

import com.example.demo.Entity.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TraitementRepository extends JpaRepository<Traitement, Long> {

    List<Traitement> findByPatientId(Long patientId);

}
