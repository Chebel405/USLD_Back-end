package com.example.demo.Service;

import com.example.demo.Dto.SoignantDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Entity.Soignant;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;
@ComponentScan
public interface SoignantService {

    SoignantDTO createSoignant(SoignantDTO soignantDTO);

    List<SoignantDTO>findAll();

    Optional<SoignantDTO>findById(Long id);

    SoignantDTO save(SoignantDTO soignantDTO);

    void deleteSoignant(Long id);

    SoignantDTO updateSoignant(Long id, SoignantDTO soignantDTO);
}
