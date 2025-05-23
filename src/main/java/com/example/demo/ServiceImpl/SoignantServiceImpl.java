package com.example.demo.ServiceImpl;


import com.example.demo.Entity.Soignant;
import com.example.demo.Repository.SoignantRepository;
import com.example.demo.Service.SoignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SoignantServiceImpl implements SoignantService {
    private final SoignantRepository soignantRepository;

    @Autowired
    public SoignantServiceImpl(SoignantRepository soignantRepository) {
        this.soignantRepository = soignantRepository;
    }

    @Override
    public Soignant createSoignant(Soignant soignant) {
        return soignantRepository.save(soignant);
    }

    @Override
    public List<Soignant> findAll() {
        return soignantRepository.findAll();
    }

    @Override
    public Optional<Soignant> findById(Long id) {
        return Optional.ofNullable(soignantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public Soignant save(Soignant soignant) {
        return soignantRepository.save(soignant);
    }

    @Override
    public void deleteSoignant(Long id) {
        soignantRepository.deleteById(id);
    }

    @Override
    public Soignant updateSoignant(Long id, Soignant soignant) {
        Optional<Soignant> optionalSoignant = soignantRepository.findById(id);
        if(optionalSoignant.isPresent()) {
            Soignant existeSoignant = optionalSoignant.get();
            existeSoignant.setNom(soignant.getNom());
            existeSoignant.setPrenom(soignant.getPrenom());
            existeSoignant.setType(soignant.getType());
            existeSoignant.setPatients(soignant.getPatients());

            return soignantRepository.save(existeSoignant);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Soignant non trouvé par ID: " + id);

        }
    }

}
