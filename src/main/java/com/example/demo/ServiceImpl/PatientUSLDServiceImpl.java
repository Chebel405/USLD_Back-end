// PatientUSLDServiceImpl.java
package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientUSLDDTO;
import com.example.demo.Entity.PatientUSLD;
import com.example.demo.Mapper.PatientMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.PatientUSLDRepository;
import com.example.demo.Service.PatientUSLDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientUSLDServiceImpl implements PatientUSLDService {

    @Autowired
    private PatientUSLDRepository patientUSLDRepository;

    /**
     * Nettoie les champs texte du DTO avant enregistrement ou mise à jour.
     *
     * 🎯 Objectif :
     * - Supprimer les espaces inutiles en début et fin de chaîne (ex : "Montagne ")
     * - Éviter les incohérences en base de données
     * - Garantir le bon fonctionnement des recherches (contains / ignoreCase)
     *
     * Cette méthode est appelée dans create() et update().
     */
    private void nettoyerChampsTexte(PatientUSLDDTO dto) {
        if (dto.getNom() != null) dto.setNom(dto.getNom().trim());
        if (dto.getPrenom() != null) dto.setPrenom(dto.getPrenom().trim());
        if (dto.getNiveauAutonomie() != null) dto.setNiveauAutonomie(dto.getNiveauAutonomie().trim());
    }
    @Override
    public PatientUSLDDTO create(PatientUSLDDTO dto) {
        PatientUSLD entity = PatientMapper.toEntity(dto);
        return PatientMapper.toDTO(patientUSLDRepository.save(entity));
    }

    @Override
    public List<PatientUSLDDTO> findAll() {
        return patientUSLDRepository.findAll().stream()
                .map(p -> PatientMapper.toDTO((PatientUSLD) p))
                .collect(Collectors.toList());
    }

    @Override
    public PatientUSLDDTO findById(Long id) {
        PatientUSLD entity = patientUSLDRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient USLD non trouvé"));
        return PatientMapper.toDTO(entity);
    }

    @Override
    public PatientUSLDDTO update(Long id, PatientUSLDDTO dto) {
        PatientUSLD existing = (PatientUSLD) patientUSLDRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient USLD non trouvé"));

        PatientUSLD updated = PatientMapper.toEntity(dto);
        updated.setId(existing.getId());
        return PatientMapper.toDTO(patientUSLDRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        patientUSLDRepository.deleteById(id);
    }

    @Override
    public List<PatientUSLDDTO> findByNom(String nom) {
        return patientUSLDRepository.findByNomContainingIgnoreCase(nom).stream()
                .map(p -> PatientMapper.toDTO((PatientUSLD) p))
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientUSLDDTO>findByPrenom(String prenom){
        return patientUSLDRepository.findByPrenomContainingIgnoreCase(prenom).stream()
                .map(p -> PatientMapper.toDTO((PatientUSLD) p))
                .collect(Collectors.toList());
    }
    @Override
    public List<PatientUSLDDTO>findByDateNaissance(LocalDate dateNaissance){
        return patientUSLDRepository.findByDateNaissance(dateNaissance).stream()
                .map(p -> PatientMapper.toDTO((PatientUSLD) p))
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientUSLDDTO> findByNumeroChambre(String numeroChambre) {
        return patientUSLDRepository.findByNumeroChambre(numeroChambre).stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientUSLDDTO> findByNiveauAutonomie(String niveau) {
        return patientUSLDRepository.findByNiveauAutonomieContainingIgnoreCase(niveau).stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }
}
