package com.example.demo.ServiceImpl;

import com.example.demo.Dto.RendezVousDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Entity.RendezVous;
import com.example.demo.Entity.Soignant;
import com.example.demo.Entity.Soin;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.RendezVousRepository;
import com.example.demo.Repository.SoignantRepository;
import com.example.demo.Repository.SoinRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RendezVousServiceImplTest {

    @Mock
    private RendezVousRepository rendezVousRepository;
    @Mock // Simule le repository des patients
    private PatientRepository patientRepository;

    @Mock // Simule le repository des soignants (utilisé par le mapper)
    private SoignantRepository soignantRepository;

    @Mock
    private SoinRepository soinRepository;
    @InjectMocks // Injecte les mocks dans le service testé
    private RendezVousServiceImpl rendezVousService;

    @Test
    void testPrendreRendezVous() {
        RendezVousDTO inputDto = new RendezVousDTO();
        inputDto.setDateHeure(LocalDateTime.of(2025, 6, 20, 10, 30));
        inputDto.setMotif("Consultation");
        inputDto.setPatientId(1L);
        inputDto.setSoignantId(2L);
        inputDto.setSoinId(3L);

        Patient patient = new Patient();
        patient.setId(1L);

        Soignant soignant = new Soignant();
        soignant.setId(2L);

        Soin soin = new Soin();
        soin.setId(3L);

        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(10L);
        rendezVous.setDateHeure(inputDto.getDateHeure());
        rendezVous.setMotif(inputDto.getMotif());
        rendezVous.setPatient(patient);
        rendezVous.setSoignant(soignant);
        rendezVous.setSoin(soin);

// MOCK
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(soignantRepository.findById(2L)).thenReturn(Optional.of(soignant));
        when(soinRepository.findById(3L)).thenReturn(Optional.of(soin));
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(rendezVous);

        // ACT
        RendezVousDTO result = rendezVousService.prendreRendezVous(inputDto);

        // ASSERT
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Consultation", result.getMotif());
        verify(rendezVousRepository).save(any(RendezVous.class));
    }

    @Test
    void testGetRendezVousByIdWhenExists(){

        Long id = 10L;

        Patient patient = new Patient();
        patient.setId(1L);
        Soignant soignant = new Soignant();
        soignant.setId(2L);
        Soin soin = new Soin();
        soin.setId(3L);

        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(id);
        rendezVous.setDateHeure(LocalDateTime.of(2025, 7, 10, 9, 0));
        rendezVous.setMotif("Radiologie");
        rendezVous.setPatient(patient);
        rendezVous.setSoignant(soignant);
        rendezVous.setSoin(soin);

        when(rendezVousRepository.findById(id)).thenReturn(Optional.of(rendezVous));

        Optional<RendezVousDTO> result = rendezVousService.getRendezVousById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("Radiologie", result.get().getMotif());


    }


}
