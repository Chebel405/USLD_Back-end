// PatientSansSoinServiceImpl.java
package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientSansSoinDTO;
import com.example.demo.Entity.PatientSansSoin;
import com.example.demo.Entity.PatientUSLD;
import com.example.demo.Mapper.PatientMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Service.PatientSansSoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.PatientSansSoinRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientSansSoinServiceImpl implements PatientSansSoinService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientSansSoinRepository patientSansSoinRepository;


    @Override
    public PatientSansSoinDTO create(PatientSansSoinDTO dto) {
        PatientSansSoin entity = PatientMapper.toEntity(dto);
        return PatientMapper.toDTO(patientRepository.save(entity));
    }

    @Override
    public List<PatientSansSoinDTO> findAll() {
        return patientRepository.findAll().stream()
                .filter(p -> p instanceof PatientSansSoin)
                .map(p -> PatientMapper.toDTO((PatientSansSoin) p))
                .collect(Collectors.toList());
    }

    @Override
    public PatientSansSoinDTO findById(Long id) {
        PatientSansSoin entity = (PatientSansSoin) patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient Sans Soin non trouvé"));
        return PatientMapper.toDTO(entity);
    }

    @Override
    public PatientSansSoinDTO update(Long id, PatientSansSoinDTO dto) {
        PatientSansSoin existing = (PatientSansSoin) patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient Sans Soin non trouvé"));

        PatientSansSoin updated = PatientMapper.toEntity(dto);
        updated.setId(existing.getId());
        return PatientMapper.toDTO(patientRepository.save(updated));
    }
    @Override
    public List<PatientSansSoinDTO> findByNom(String nom) {
        return patientSansSoinRepository.findByNomContainingIgnoreCase(nom)
                .stream()
                .map(p -> PatientMapper.toDTO((PatientSansSoin) p))
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientSansSoinDTO> findByPrenom(String prenom) {
        return patientSansSoinRepository.findByPrenomContainingIgnoreCase(prenom).stream()
                .map(p -> PatientMapper.toDTO((PatientSansSoin) p))
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientSansSoinDTO> findByDateNaissance(LocalDate dateNaissance) {
        return patientSansSoinRepository.findByDateNaissance(dateNaissance).stream()
                .map(p -> PatientMapper.toDTO((PatientSansSoin) p))
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientSansSoinDTO> findByNumeroChambre(Integer numeroChambre) {
        return patientSansSoinRepository.findByNumeroChambre(numeroChambre).stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientSansSoinDTO> findByNiveauAutonomie(String niveau) {
        return patientSansSoinRepository.findByNiveauAutonomieContainingIgnoreCase(niveau).stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
