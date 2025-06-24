package com.example.demo.ServiceImpl;

import com.example.demo.Dto.UtilisateurDTO;
import com.example.demo.Entity.Utilisateur;
import com.example.demo.Mapper.UtilisateurMapper;

import com.example.demo.Repository.UtilisateurRepository;
import com.example.demo.Service.UtilisateurService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDTO createUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = UtilisateurMapper.toEntity(utilisateurDTO);
        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return UtilisateurMapper.toDTO(saved);

    }

    @Override
    public List<UtilisateurDTO> findAll() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UtilisateurDTO> findById(Long id) {
        return utilisateurRepository.findById(id)
                .map(UtilisateurMapper::toDTO);
    }

    @Override
    public UtilisateurDTO save(UtilisateurDTO dto) {
        Utilisateur entity = UtilisateurMapper.toEntity(dto);
        return UtilisateurMapper.toDTO(utilisateurRepository.save(entity));
    }

    @Override
    public UtilisateurDTO updateUtilisateur(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur existing = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec l'ID: " + id));

        // Conversion DTO → Entity avec les nouveaux champs
        Utilisateur updated = UtilisateurMapper.toEntity(utilisateurDTO);
        updated.setId(existing.getId());
        return UtilisateurMapper.toDTO(utilisateurRepository.save(updated));
    }
    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
