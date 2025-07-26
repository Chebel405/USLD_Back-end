// PatientAlzheimerServiceImpl.java
package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientAlzheimerDTO;
import com.example.demo.Dto.PatientSansSoinDTO;
import com.example.demo.Entity.PatientAlzheimer;
import com.example.demo.Mapper.PatientMapper;
import com.example.demo.Repository.PatientAlzheimerRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.PatientSansSoinRepository;
import com.example.demo.Service.PatientAlzheimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientAlzheimerServiceImpl implements PatientAlzheimerService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientAlzheimerRepository patientAlzheimerRepository;

    @Override
    public PatientAlzheimerDTO create(PatientAlzheimerDTO dto) {
        PatientAlzheimer entity = PatientMapper.toEntity(dto);
        return PatientMapper.toDTO(patientRepository.save(entity));
    }

    @Override
    public List<PatientAlzheimerDTO> findAll() {
        return patientRepository.findAll().stream()
                .filter(p -> p instanceof PatientAlzheimer)
                .map(p -> PatientMapper.toDTO((PatientAlzheimer) p))
                .collect(Collectors.toList());
    }

    @Override
    public PatientAlzheimerDTO findById(Long id) {
        PatientAlzheimer entity = (PatientAlzheimer) patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient Alzheimer non trouvé"));
        return PatientMapper.toDTO(entity);
    }

    @Override
    public PatientAlzheimerDTO update(Long id, PatientAlzheimerDTO dto) {
        PatientAlzheimer existing = (PatientAlzheimer) patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient Alzheimer non trouvé"));

        PatientAlzheimer updated = PatientMapper.toEntity(dto);
        updated.setId(existing.getId());
        return PatientMapper.toDTO(patientRepository.save(updated));
    }

    @Override
    public List<PatientAlzheimerDTO> findByNom(String nom) {
        return patientAlzheimerRepository.findByNomContainingIgnoreCase(nom)
                .stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
