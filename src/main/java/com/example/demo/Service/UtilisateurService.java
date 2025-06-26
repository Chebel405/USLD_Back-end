package com.example.demo.Service;

import com.example.demo.Dto.UtilisateurDTO;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
   UtilisateurDTO createUtilisateur(UtilisateurDTO utilisateurDTO);

    List<UtilisateurDTO> findAll();

    Optional<UtilisateurDTO> findById(Long id);

    UtilisateurDTO save(UtilisateurDTO patientDTO);

    void deleteUtilisateur(Long id);

    UtilisateurDTO updateUtilisateur(Long id, UtilisateurDTO utilisateurDTO);

}
