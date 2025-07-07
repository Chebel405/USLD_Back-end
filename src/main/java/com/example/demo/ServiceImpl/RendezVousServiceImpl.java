package com.example.demo.ServiceImpl;

import com.example.demo.Dto.RendezVousDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Entity.RendezVous;
import com.example.demo.Entity.Soignant;
import com.example.demo.Entity.Soin;
import com.example.demo.Mapper.RendezVousMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.RendezVousRepository;
import com.example.demo.Repository.SoignantRepository;
import com.example.demo.Repository.SoinRepository;
import com.example.demo.Service.RendezVousService;
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
public class RendezVousServiceImpl implements RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final PatientRepository patientRepository;
    private final SoignantRepository soignantRepository;

    private final SoinRepository soinRepository;

    @Autowired
    public RendezVousServiceImpl(RendezVousRepository rendezVousRepository,
                                 PatientRepository patientRepository,
                                 SoignantRepository soignantRepository,
                                 SoinRepository soinRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.patientRepository = patientRepository;
        this.soignantRepository = soignantRepository;
        this.soinRepository = soinRepository;
    }

    @Override
    public RendezVousDTO prendreRendezVous(RendezVousDTO rendezVousDTO) {
        Patient patient = patientRepository.findById(rendezVousDTO.getPatientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient non trouvé"));

        Soignant soignant = soignantRepository.findById(rendezVousDTO.getSoignantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soignant non trouvé"));

        Soin soin = null;
        if (rendezVousDTO.getSoinId() != null) {
            soin = soinRepository.findById(rendezVousDTO.getSoinId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soin non trouvé"));
        }

        RendezVous rendezVous = RendezVousMapper.toEntity(rendezVousDTO, patient, soignant, soin);
        RendezVous saved = rendezVousRepository.save(rendezVous);
        return RendezVousMapper.toDTO(saved);
    }

   @Override
   public List<RendezVousDTO>getAllRendezVous(){
        return rendezVousRepository.findAll().stream()
                .map(RendezVousMapper::toDTO)
                .collect(Collectors.toList());
   }

    @Override
    public Optional<RendezVousDTO> getRendezVousById(Long id) {
        return rendezVousRepository.findById(id)
                .map(RendezVousMapper::toDTO);
    }
    

    @Override
    public RendezVousDTO updateRendezVous(Long id, RendezVousDTO rendezVousDTO) {

        // 1. On récupère le rendez-vous existant
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rendez-vous introuvable"));

        // 2. On récupère le patient, le soignant et le soin via leur propre ID
        Patient patient = patientRepository.findById(rendezVousDTO.getPatientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient introuvable"));

        Soignant soignant = soignantRepository.findById(rendezVousDTO.getSoignantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soignant introuvable"));

        Soin soin = soinRepository.findById(rendezVousDTO.getSoinId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soin introuvable"));

        // 3. Mise à jour des données
        rendezVous.setDateHeure(rendezVousDTO.getDateHeure());
        rendezVous.setMotif(rendezVousDTO.getMotif());
        rendezVous.setPatient(patient);
        rendezVous.setSoignant(soignant);
        rendezVous.setSoin(soin);

        // 4. Sauvegarde et retour
        RendezVous updated = rendezVousRepository.save(rendezVous);
        return RendezVousMapper.toDTO(updated);
    }
    @Override
    public void deleteRendezVous(Long id) {
        if (!rendezVousRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rendez-vous introuvable");
        }
        rendezVousRepository.deleteById(id);
    }

    @Override
    public RendezVousDTO save(RendezVousDTO rendezVousDTO) {
        Patient patient = patientRepository.findById(rendezVousDTO.getPatientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient non trouvé"));

        Soignant soignant = soignantRepository.findById(rendezVousDTO.getSoignantId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soignant non trouvé"));

        Soin soin = null;
        if (rendezVousDTO.getSoinId() != null) {
            soin = soinRepository.findById(rendezVousDTO.getSoinId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Soin non trouvé"));
        }

        RendezVous rendezVous = RendezVousMapper.toEntity(rendezVousDTO, patient, soignant, soin);
        RendezVous saved = rendezVousRepository.save(rendezVous);
        return RendezVousMapper.toDTO(saved);
    }






}
