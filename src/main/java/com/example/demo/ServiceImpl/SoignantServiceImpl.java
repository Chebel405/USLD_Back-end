package com.example.demo.ServiceImpl;


import com.example.demo.Dto.PatientDTO;
import com.example.demo.Dto.SoignantDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Entity.Soignant;
import com.example.demo.Mapper.SoignantMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.SoignantRepository;
import com.example.demo.Service.SoignantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SoignantServiceImpl implements SoignantService {
    private final SoignantRepository soignantRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public SoignantServiceImpl(SoignantRepository soignantRepository, PatientRepository patientRepository) {
        this.soignantRepository = soignantRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public SoignantDTO createSoignant(SoignantDTO soignantDTO) {
        Soignant soignant = SoignantMapper.toEntity(soignantDTO, patientRepository);
        return SoignantMapper.toDTO(soignantRepository.save(soignant));
    }

    @Override
    public List<SoignantDTO> findAll() {
        return soignantRepository.findAllWithPatients()
                .stream()
                .map(SoignantMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SoignantDTO> findById(Long id) {
        return soignantRepository.findById(id)
                .map(SoignantMapper::toDTO);
    }

    @Override
    public SoignantDTO save(SoignantDTO dto) {
        Soignant entity = SoignantMapper.toEntity(dto, patientRepository);
        return SoignantMapper.toDTO(soignantRepository.save(entity));
    }

    @Override
    public SoignantDTO updateSoignant(Long id, SoignantDTO soignantDTO) {
        Soignant existing = soignantRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soignant non trouvé par ID: " + id));

            existing.setNom(soignantDTO.getNom());
            existing.setPrenom(soignantDTO.getPrenom());
            existing.setType(soignantDTO.getType());


        // Met à jour la liste des patients (si présente dans le DTO)
        if (soignantDTO.getPatientsIds() != null) {
            List<Patient> patients = soignantDTO.getPatientsIds().stream()
                    .map(pid -> patientRepository.findById(pid).orElse(null))
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
            existing.setPatients(patients);

            // Synchroniser côté patient -> ajouter le soignant à chaque patient
            for (Patient p : patients) {
                if (!p.getSoignants().contains(existing)) {
                    p.getSoignants().add(existing);
                    patientRepository.save(p);
                }
            }
        }

        Soignant updated = soignantRepository.save(existing);
        return SoignantMapper.toDTO(updated);


    }



    @Override
    public void deleteSoignant(Long id) {
        soignantRepository.deleteById(id);
    }




}
