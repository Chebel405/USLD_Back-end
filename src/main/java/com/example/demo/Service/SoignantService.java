package com.example.demo.Service;

import com.example.demo.Entity.Patient;
import com.example.demo.Entity.Soignant;

import java.util.List;
import java.util.Optional;

public interface SoignantService {

    Soignant createSoignant(Soignant soignant);

    List<Soignant> findAll();

    Optional<Soignant> findById(Long id);

    Soignant save(Soignant soignant);

    void deleteSoignant(Long id);

    Soignant updateSoignant(Long id, Soignant soignant);
}
