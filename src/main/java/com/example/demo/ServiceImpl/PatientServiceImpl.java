package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Mapper.PatientMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.SoignantRepository;
import com.example.demo.Service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final SoignantRepository soignantRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, SoignantRepository soignantRepository) {
        this.patientRepository = patientRepository;
        this.soignantRepository = soignantRepository;
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = PatientMapper.toEntity(patientDTO, soignantRepository);
        return PatientMapper.toDTO(patientRepository.save(patient));
    }

    @Override
    public List<PatientDTO> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientDTO> findById(Long id) {
        return patientRepository.findById(id)
                .map(PatientMapper::toDTO);
    }

    @Override
    public PatientDTO save(PatientDTO dto) {
        Patient entity = PatientMapper.toEntity(dto, soignantRepository);
        return PatientMapper.toDTO(patientRepository.save(entity));
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

        Patient updated = PatientMapper.toEntity(patientDTO, soignantRepository);
        updated.setId(existing.getId());
        return PatientMapper.toDTO(patientRepository.save(updated));
    }
    @Override
    public void deletePatient(Long id) {

        patientRepository.deleteById(id);
    }

}
