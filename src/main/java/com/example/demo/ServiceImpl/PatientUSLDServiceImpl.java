// PatientUSLDServiceImpl.java
package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientUSLDDTO;
import com.example.demo.Entity.PatientUSLD;
import com.example.demo.Mapper.PatientMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Service.PatientUSLDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientUSLDServiceImpl implements PatientUSLDService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientUSLDDTO create(PatientUSLDDTO dto) {
        PatientUSLD entity = PatientMapper.toEntity(dto);
        return PatientMapper.toDTO(patientRepository.save(entity));
    }

    @Override
    public List<PatientUSLDDTO> findAll() {
        return patientRepository.findAll().stream()
                .filter(p -> p instanceof PatientUSLD)
                .map(p -> PatientMapper.toDTO((PatientUSLD) p))
                .collect(Collectors.toList());
    }

    @Override
    public PatientUSLDDTO findById(Long id) {
        PatientUSLD entity = (PatientUSLD) patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient USLD non trouvé"));
        return PatientMapper.toDTO(entity);
    }

    @Override
    public PatientUSLDDTO update(Long id, PatientUSLDDTO dto) {
        PatientUSLD existing = (PatientUSLD) patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient USLD non trouvé"));

        PatientUSLD updated = PatientMapper.toEntity(dto);
        updated.setId(existing.getId());
        return PatientMapper.toDTO(patientRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
