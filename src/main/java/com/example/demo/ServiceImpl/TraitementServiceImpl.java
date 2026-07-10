package com.example.demo.ServiceImpl;

import com.example.demo.Dto.TraitementDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Mapper.TraitementMapper;
import com.example.demo.Entity.Traitement;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.TraitementRepository;
import com.example.demo.Service.TraitementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TraitementServiceImpl implements TraitementService {

    private final TraitementRepository traitementRepository;
    private final PatientRepository patientRepository;

    public TraitementServiceImpl(TraitementRepository traitementRepository, PatientRepository patientRepository) {
        this.traitementRepository = traitementRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public TraitementDTO createTraitement(TraitementDTO traitementDTO) {
        Traitement traitement = TraitementMapper.toEntity(traitementDTO);

        Patient patient = patientRepository.findById(traitementDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

                traitement.setPatient(patient);

                Traitement savedTraitement = traitementRepository.save(traitement);

        return TraitementMapper.toDTO(savedTraitement);
    }

    @Override
    public List<TraitementDTO> findAll() {

        return traitementRepository.findAll()
                .stream()
                .map(TraitementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TraitementDTO getTraitementById(Long id) {
        Traitement traitement = traitementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Traitement non trouvé avec l'id : " + id));

        return TraitementMapper.toDTO(traitement);
    }

    @Override
    public TraitementDTO updateTraitement(Long id, TraitementDTO traitementDTO) {
        Traitement existing = traitementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Traitement non trouvé avec l'id : " + id));

        existing.setNomMedicament(traitementDTO.getNomMedicament());
        existing.setDosageMg(traitementDTO.getDosageMg());
        existing.setDateDebut(traitementDTO.getDateDebut());
        existing.setDateFin(traitementDTO.getDateFin());
        existing.setPriseMatin(traitementDTO.getPriseMatin());
        existing.setPriseMidi(traitementDTO.getPriseMidi());
        existing.setPriseSoir(traitementDTO.getPriseSoir());
        existing.setPriseCoucher(traitementDTO.getPriseCoucher());
        existing.setObservations(traitementDTO.getObservations());

        if (traitementDTO.getPatientId() != null) {
            Patient patient = patientRepository.findById(traitementDTO.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient non trouvé"));

            existing.setPatient(patient);
    }
        Traitement updatedTraitement = traitementRepository.save(existing);

        return TraitementMapper.toDTO(updatedTraitement);
    }

    @Override
    public void deleteTraitement(Long id) {

        if(!traitementRepository.existsById(id)){
            throw new RuntimeException("Traitement non trouvé avec l'id : " + id);
        }
        traitementRepository.deleteById(id);
    }

    @Override
    public List<TraitementDTO> getTraitementsByPatientId(Long patientId) {
        return traitementRepository.findByPatientId(patientId)
                .stream()
                .map(TraitementMapper::toDTO)
                .collect(Collectors.toList());
    }
}
