package com.example.demo.ServiceImpl;


import com.example.demo.Dto.SoinDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Entity.PatientUSLD;
import com.example.demo.Entity.Soignant;
import com.example.demo.Entity.Soin;
import com.example.demo.Enums.TypeSoins;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SoinServiceImplTest {

    @Mock // Simule le repository des patients
    private SoignantRepository soignantRepository;

    @Mock // Simule le repository des soignants (utilisé par le mapper)
    private PatientRepository patientRepository;

    @Mock // Simule le repository des soignants (utilisé par le mapper)
    private RendezVousRepository rendezVousRepository;

    @Mock // Simule le repository des soignants (utilisé par le mapper)
    private SoinRepository soinRepository;


    @InjectMocks // Injecte les mocks dans le service testé
    private SoinServiceImpl soinService;

    /**
     * ✅ Teste la création d'un soin :
     * Vérifie que le service permet d'enregistrer un soin à partir d'un DTO contenant
     * une date, une description, un type, ainsi que les identifiants d’un patient et d’un soignant.
     * Le test simule la récupération du patient et du soignant, puis vérifie que le soin est bien sauvegardé.
     */
    @Test
    void testCreateSoin_shouldReturnCreatedSoinDTO() {
        // --- ARRANGE --- Création du DTO en entrée
        SoinDTO inputDto = new SoinDTO();
        inputDto.setDate(LocalDate.of(2025, 8, 12));
        inputDto.setType(TypeSoins.PSYCHOLOGIE);
        inputDto.setDescription("Séance de psychologie");
        inputDto.setPatientId(1L);
        inputDto.setSoignantId(2L);

        // Création des entités fictives associées
       // Patient patient = new Patient();
        PatientUSLD patientUSLD = new PatientUSLD();
        patientUSLD.setId(1L);

        Soignant soignant = new Soignant();
        soignant.setId(2L);

        // Soin généré par le mapper
        Soin mappedSoin = new Soin();
        mappedSoin.setDate(inputDto.getDate());
        mappedSoin.setType(inputDto.getType());
        mappedSoin.setDescription(inputDto.getDescription());
        mappedSoin.setPatient(patientUSLD);
        mappedSoin.setSoignant(soignant);

        // Soin sauvegardé simulé (avec ID généré)
        Soin savedSoin = new Soin();
        savedSoin.setId(100L);
        savedSoin.setDate(inputDto.getDate());
        savedSoin.setType(inputDto.getType());
        savedSoin.setDescription(inputDto.getDescription());
        savedSoin.setPatient(patientUSLD);
        savedSoin.setSoignant(soignant);

        // --- MOCK --- des dépendances
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientUSLD));
        when(soignantRepository.findById(2L)).thenReturn(Optional.of(soignant));
        when(soinRepository.save(any(Soin.class))).thenReturn(savedSoin);

        // --- ACT --- appel du service
        SoinDTO result = soinService.createSoin(inputDto);

        // --- ASSERT --- vérification du retour
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Séance de psychologie", result.getDescription());
        assertEquals(TypeSoins.PSYCHOLOGIE, result.getType());

        // Vérifie que le soin a bien été sauvegardé
        verify(soinRepository).save(any(Soin.class));
    }

    /**
     * ✅ Teste la récupération de tous les soins :
     * Simule deux entités `Soin` avec des patients et soignants associés, puis vérifie
     * que le service retourne correctement une liste de `SoinDTO` correspondants.
     * Ce test permet de valider la logique de transformation et d’agrégation des entités en DTOs.
     */

    @Test
    void testFindAll_shouldReturnListOfSoinDTO() {
        // --- ARRANGE --- Création des entités fictives
        //Patient patient = new Patient();
        PatientUSLD patientUSLD = new PatientUSLD();
        patientUSLD.setId(1L);
        Soignant soignant = new Soignant();
        soignant.setId(2L);

        Soin soin1 = new Soin();
        soin1.setId(1L);
        soin1.setDate(LocalDate.of(2025, 6, 15));
        soin1.setType(TypeSoins.KINESITHERAPIE);
        soin1.setDescription("Séance kiné");
        soin1.setPatient(patientUSLD);
        soin1.setSoignant(soignant);

        Soin soin2 = new Soin();
        soin2.setId(2L);
        soin2.setDate(LocalDate.of(2025, 6, 16));
        soin2.setType(TypeSoins.PSYCHOLOGIE);
        soin2.setDescription("Suivi psy");
        soin2.setPatient(patientUSLD);
        soin2.setSoignant(soignant);

        List<Soin> soins = List.of(soin1, soin2);

        // --- MOCK --- le repository retourne la liste simulée
        when(soinRepository.findAll()).thenReturn(soins);

        // --- ACT --- appel de la méthode à tester
        List<SoinDTO> result = soinService.findAll();

        // --- ASSERT --- vérifie les résultats
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Séance kiné", result.get(0).getDescription());
        assertEquals("Suivi psy", result.get(1).getDescription());
        verify(soinRepository).findAll();
    }

    /** Test qui vérifie que la méthode findById retourne un Optional vide
     *  lorsque le soin n'existe pas dans le repository.
     */
    @Test
    void testFindById_whenNotFound_shouldReturnEmptyOptional() {
        // Simule un ID inexistant
        Long id = 999L;

        // --- MOCK ---
        when(soinRepository.findById(id)).thenReturn(Optional.empty());

        // --- ACT ---
        Optional<SoinDTO> result = soinService.findById(id);

        // --- ASSERT ---
        assertTrue(result.isEmpty());
        verify(soinRepository, times(1)).findById(id);
    }

    /** Test qui vérifie que la méthode updateSoin met bien à jour les informations
     *  d’un soin existant et retourne le SoinDTO mis à jour.
     */
    @Test
    void testUpdateSoin_whenExists_shouldUpdateAndReturnDTO() {
        Long id = 1L;

        SoinDTO inputDto = new SoinDTO();
        inputDto.setDate(LocalDate.of(2025, 9, 1));
        inputDto.setType(TypeSoins.RADIOLOGIE);
        inputDto.setDescription("Nouvelle description");
        inputDto.setPatientId(1L);
        inputDto.setSoignantId(2L);

        PatientUSLD patientUSLD = new PatientUSLD();
        patientUSLD.setId(1L);

        Soignant soignant = new Soignant();
        soignant.setId(2L);

        Soin existingSoin = new Soin();
        existingSoin.setId(id);
        existingSoin.setDate(LocalDate.of(2025, 8, 1));
        existingSoin.setType(TypeSoins.PSYCHOLOGIE);
        existingSoin.setDescription("Ancienne description");

        when(soinRepository.findById(id)).thenReturn(Optional.of(existingSoin));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientUSLD));
        when(soignantRepository.findById(2L)).thenReturn(Optional.of(soignant));
        when(soinRepository.save(any(Soin.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SoinDTO result = soinService.updateSoin(id, inputDto);

        assertNotNull(result);
        assertEquals("Nouvelle description", result.getDescription());
        assertEquals(TypeSoins.RADIOLOGIE, result.getType());
    }


    /** Vérifie que si l’on tente de mettre à jour un soin inexistant,
     *  une exception ResponseStatusException est levée avec le bon message.
     */
    @Test
    void testUpdateSoin_whenNotFound_shouldThrowException() {
        Long id = 42L;

        SoinDTO inputDto = new SoinDTO();
        inputDto.setDescription("Tentative de mise à jour");

        when(soinRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            soinService.updateSoin(id, inputDto);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Soins non trouvé par ID " + id, exception.getReason());
    }

    /**
     *  Vérifie que la suppression fonctionne correctement lorsqu’un soin existe,
     *  en s’assurant que la méthode deleteById est bien appelée.
     */
    @Test
    void testDeleteSoin_whenExists_shouldDeleteSuccessfully() {
        Long id = 20L;

        when(soinRepository.existsById(id)).thenReturn(true);
        doNothing().when(soinRepository).deleteById(id);

        soinService.deleteSoin(id);

        verify(soinRepository).existsById(id);
        verify(soinRepository).deleteById(id);
    }

    /**
     *  Vérifie qu’une tentative de suppression d’un soin inexistant
     *  déclenche une exception ResponseStatusException avec le bon message,
     *  et que la méthode deleteById n’est pas appelée.
     */
    @Test
    void testDeleteSoin_whenNotFound_shouldThrowException() {
        Long id = 404L;

        when(soinRepository.existsById(id)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            soinService.deleteSoin(id);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Soin non trouvé avec l'id : " + id, exception.getReason());
        verify(soinRepository).existsById(id);
        verify(soinRepository, never()).deleteById(any());
    }






}
