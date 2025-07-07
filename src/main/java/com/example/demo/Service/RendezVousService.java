package com.example.demo.Service;

import com.example.demo.Dto.RendezVousDTO;

import java.util.List;
import java.util.Optional;

public interface RendezVousService {
    RendezVousDTO prendreRendezVous(RendezVousDTO rendezVousDTO);

    List<RendezVousDTO>getAllRendezVous();

    Optional<RendezVousDTO> getRendezVousById(Long id);

    RendezVousDTO save(RendezVousDTO rendezVousDTO);

    void deleteRendezVous(Long id);

    RendezVousDTO updateRendezVous(Long id, RendezVousDTO rendezVousDTO);


}
