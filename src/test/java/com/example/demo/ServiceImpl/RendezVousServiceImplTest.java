package com.example.demo.ServiceImpl;

import com.example.demo.Dto.RendezVousDTO;
import com.example.demo.Entity.*;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.RendezVousRepository;
import com.example.demo.Repository.SoignantRepository;
import com.example.demo.Repository.SoinRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RendezVousServiceImplTest {

    // Mocks des repositories nécessaires
    @Mock
    private RendezVousRepository rendezVousRepository;
    @Mock
    private PatientRepository patientRepository;

    @Mock
    private SoignantRepository soignantRepository;

    @Mock
    private SoinRepository soinRepository;

    // Service à tester avec les mocks injectés
    @InjectMocks
    private RendezVousServiceImpl rendezVousService;

    /**
     * 🔍 Test de la méthode prendreRendezVous()
     * Vérifie que la création d'un rendez-vous fonctionne correctement :
     * - les entités Patient, Soignant, Soin sont bien retrouvées
     * - le rendez-vous est bien enregistré et retourné sous forme de DTO
     */
    @Test
    void testPrendreRendezVous() {
        RendezVousDTO inputDto = new RendezVousDTO();
        inputDto.setDateHeure(LocalDateTime.of(2025, 6, 20, 10, 30));
        inputDto.setMotif("Consultation");
        inputDto.setPatientId(1L);
        inputDto.setSoignantId(2L);
        inputDto.setSoinId(3L);


        // Patient patient = new Patient();
        PatientUSLD patientUSLD = new PatientUSLD();
        patientUSLD.setId(1L);

        Soignant soignant = new Soignant();
        soignant.setId(2L);

        Soin soin = new Soin();
        soin.setId(3L);

        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(10L);
        rendezVous.setDateHeure(inputDto.getDateHeure());
        rendezVous.setMotif(inputDto.getMotif());
        rendezVous.setPatient(patientUSLD);
        rendezVous.setSoignant(soignant);
        rendezVous.setSoin(soin);

// MOCK
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientUSLD));
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

    /**
     * 🔍 Test de la méthode getRendezVousById() quand un rendez-vous existe
     * Vérifie que le rendez-vous est retrouvé en base et converti en DTO
     */
    @Test
    void testGetRendezVousByIdWhenExists(){

        Long id = 10L;

        PatientUSLD patientUSLD = new PatientUSLD();
        patientUSLD.setId(1L);
        Soignant soignant = new Soignant();
        soignant.setId(2L);
        Soin soin = new Soin();
        soin.setId(3L);

        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(id);
        rendezVous.setDateHeure(LocalDateTime.of(2025, 7, 10, 9, 0));
        rendezVous.setMotif("Radiologie");
        rendezVous.setPatient(patientUSLD);
        rendezVous.setSoignant(soignant);
        rendezVous.setSoin(soin);

        when(rendezVousRepository.findById(id)).thenReturn(Optional.of(rendezVous));

        Optional<RendezVousDTO> result = rendezVousService.getRendezVousById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("Radiologie", result.get().getMotif());

    }

    /**
     * 🔄 Test de la méthode updateRendezVous()
     * Vérifie que les champs du rendez-vous sont bien mis à jour :
     * - récupération du rendez-vous existant
     * - mise à jour avec les nouvelles valeurs du DTO
     * - sauvegarde et retour du résultat mis à jour
     */
    @Test
    void testUpdateRendezVous(){
        Long id = 10L;

        RendezVousDTO inputDto = new RendezVousDTO();
        inputDto.setId(id);
        inputDto.setDateHeure(LocalDateTime.of(2025,7,10,14, 0));
        inputDto.setMotif("Chirurgie de contrôle");
        inputDto.setPatientId(1L);
        inputDto.setSoignantId(2L);
        inputDto.setSoinId(3L);

        PatientUSLD patientUSLD = new PatientUSLD();
        patientUSLD.setId(1L);

        Soignant soignant = new Soignant();
        soignant.setId(2L);

        Soin soin = new Soin();
        soin.setId(3L);

        RendezVous existingRdv = new RendezVous();
        existingRdv.setId(id);
        existingRdv.setDateHeure(LocalDateTime.of(2025,7,1,9,0));
        existingRdv.setMotif("Ancien motif");
        existingRdv.setPatient(patientUSLD);
        existingRdv.setSoignant(soignant);
        existingRdv.setSoin(soin);

        when(rendezVousRepository.findById(id)).thenReturn(Optional.of(existingRdv));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientUSLD));
        when(soignantRepository.findById(2L)).thenReturn(Optional.of(soignant));
        when(soinRepository.findById(3L)).thenReturn(Optional.of(soin));
        when(rendezVousRepository.save(any(RendezVous.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RendezVousDTO result = rendezVousService.updateRendezVous(id, inputDto);

        assertNotNull(result);
        assertEquals("Chirurgie de contrôle", result.getMotif());
        assertEquals(LocalDateTime.of(2025, 7, 10, 14, 0), result.getDateHeure());
        verify(rendezVousRepository).save(any(RendezVous.class));


    }

    /**
     * 🗑️ Test de la méthode deleteRendezVous() quand le rendez-vous existe
     * Vérifie que la suppression est effectuée uniquement si l'ID existe
     */
    @Test
    void testDeleteRendezVous(){

        Long id = 15L;
        when(rendezVousRepository.existsById(id)).thenReturn(true);
        doNothing().when(rendezVousRepository).deleteById(id);

        rendezVousService.deleteRendezVous(id);

        verify(rendezVousRepository, times(1)).existsById(id);
        verify(rendezVousRepository, times(1)).deleteById(id);

    }

    /**
     * ❌ Test de la méthode deleteRendezVous() quand le rendez-vous n'existe pas
     * Vérifie que l’exception ResponseStatusException avec code 404 est bien levée
     * et que la suppression n’est pas exécutée.
     */
    @Test
    void testDeleteRendezVous_whenNotFound_shouldThrowException() {
        // ARRANGE
        Long id = 99L;
        when(rendezVousRepository.existsById(id)).thenReturn(false);

        // ACT + ASSERT
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            rendezVousService.deleteRendezVous(id);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Rendez-vous introuvable", exception.getReason());
        verify(rendezVousRepository, times(1)).existsById(id);
        verify(rendezVousRepository, never()).deleteById(any());
    }

    /**
     * ✅ Ce test vérifie que la méthode getAllRendezVous() renvoie bien la liste des rendez-vous sous forme de DTO.
     * Pour cela, on crée deux objets RendezVous avec des relations valides (Patient, Soignant, Soin),
     * puis on simule leur retour depuis le repository.
     * On vérifie ensuite que la taille de la liste retournée est correcte
     * et que les champs du premier rendez-vous correspondent à ce qu’on attend.
     */

    @Test
    void testGetAllRendezVous() {
        // ARRANGE
        PatientUSLD patientUSLD = new PatientUSLD();
        patientUSLD.setId(1L);

        Soignant soignant = new Soignant();
        soignant.setId(2L);

        Soin soin = new Soin();
        soin.setId(3L);

        RendezVous rdv1 = new RendezVous();
        rdv1.setId(1L);
        rdv1.setMotif("Consultation");
        rdv1.setDateHeure(LocalDateTime.of(2025, 6, 20, 10, 0));
        rdv1.setPatient(patientUSLD);
        rdv1.setSoignant(soignant);
        rdv1.setSoin(soin);

        RendezVous rdv2 = new RendezVous();
        rdv2.setId(2L);
        rdv2.setMotif("Examen");
        rdv2.setDateHeure(LocalDateTime.of(2025, 6, 21, 11, 0));
        rdv2.setPatient(patientUSLD);
        rdv2.setSoignant(soignant);
        rdv2.setSoin(soin);

        when(rendezVousRepository.findAll()).thenReturn(List.of(rdv1, rdv2));

        // ACT
        var result = rendezVousService.getAllRendezVous();

        // ASSERT
        assertEquals(2, result.size());
        assertEquals("Consultation", result.get(0).getMotif());
        assertEquals("Examen", result.get(1).getMotif());
        verify(rendezVousRepository).findAll();
    }





}
