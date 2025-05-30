package com.example.demo.ServiceImpl;

import com.example.demo.Dto.SoinDTO;
import com.example.demo.Entity.Soin;
import com.example.demo.Mapper.SoinMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.SoignantRepository;
import com.example.demo.Repository.SoinRepository;
import com.example.demo.Service.SoinService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SoinServiceImpl implements SoinService {

    private final SoinRepository soinRepository;
    private final PatientRepository patientRepository;
    private final SoignantRepository soignantRepository;

    public SoinServiceImpl(SoinRepository soinRepository, PatientRepository patientRepository, SoignantRepository soignantRepository) {
        this.soinRepository = soinRepository;
        this.patientRepository = patientRepository;
        this.soignantRepository = soignantRepository;
    }


    @Override
    public SoinDTO createSoin(SoinDTO soinDTO) {
        Soin soin = SoinMapper.toEntity(soinDTO, patientRepository, soignantRepository);
        return SoinMapper.toDTO(soinRepository.save(soin));
    }

    @Override
    public List<SoinDTO> findAll() {
        return soinRepository.findAll()
                .stream()
                .map(SoinMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SoinDTO> findById(Long id) {
        return soinRepository.findById(id)
                .map(SoinMapper::toDTO);
    }

    @Override
    public SoinDTO save(SoinDTO dto) {
        Soin entity = SoinMapper.toEntity(dto, patientRepository, soignantRepository );
        return SoinMapper.toDTO(soinRepository.save(entity));
    }

    @Override
    public SoinDTO updateSoin(Long id, SoinDTO soinDTO) {
        Soin existing = soinRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soins non trouvé par ID " + id));
        existing.setDate(soinDTO.getDate());
        existing.setDescription(soinDTO.getDescription());
        existing.setType(soinDTO.getType());

        // Mise à jour du patient s'il existe
        if (soinDTO.getPatientId() != null) {
            patientRepository.findById(soinDTO.getPatientId())
                    .ifPresent(existing::setPatient);
        }

        // Mise à jour du soignant s'il existe
        if (soinDTO.getSoignantId() != null) {
            soignantRepository.findById(soinDTO.getSoignantId())
                    .ifPresent(existing::setSoignant);
        }
        return SoinMapper.toDTO(soinRepository.save(existing));
    }

    @Override
    public void deleteSoin(Long id) {
        if (!soinRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soin non trouvé avec l'id : " + id);
        }
        soinRepository.deleteById(id);

    }
}
