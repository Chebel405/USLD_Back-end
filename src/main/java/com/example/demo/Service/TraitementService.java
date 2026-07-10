package com.example.demo.Service;

import com.example.demo.Dto.TraitementDTO;

import java.util.List;

public interface TraitementService {

    TraitementDTO createTraitement(TraitementDTO traitementDTO);

    List<TraitementDTO> findAll();

    TraitementDTO getTraitementById(Long id);

    TraitementDTO updateTraitement(Long id, TraitementDTO traitementDTO);

    void deleteTraitement(Long id);
    List<TraitementDTO> getTraitementsByPatientId(Long patientId);
}
