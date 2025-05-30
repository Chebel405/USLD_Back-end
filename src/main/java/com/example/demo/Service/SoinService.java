package com.example.demo.Service;

import com.example.demo.Dto.SoignantDTO;
import com.example.demo.Dto.SoinDTO;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Optional;
@ComponentScan
public interface SoinService {

    SoinDTO createSoin(SoinDTO soinDTO);

    List<SoinDTO> findAll();

    Optional<SoinDTO> findById(Long id);

    SoinDTO save(SoinDTO soinDTO);

    void deleteSoin(Long id);

    SoinDTO updateSoin(Long id, SoinDTO soinDTO);
}
