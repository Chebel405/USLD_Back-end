package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientDTO;
import com.example.demo.Entity.Patient;
import com.example.demo.Enums.TypePatient;
import com.example.demo.Mapper.PatientMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.SoignantRepository;
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
import static org.mockito.Mockito.*;

// Active l'extension Mockito pour JUnit 5
@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @Mock // Simule le repository des patients
    private PatientRepository patientRepository;

    @Mock // Simule le repository des soignants (utilisé par le mapper)
    private SoignantRepository soignantRepository;

    @InjectMocks // Injecte les mocks dans le service testé
    private PatientServiceImpl patientService;

    @Test
    void testCreatePatient() {
        // --- ARRANGE --- Création de l'entrée DTO
        PatientDTO inputDto = new PatientDTO();
        inputDto.setNom("Durand");
        inputDto.setPrenom("Paul");
        inputDto.setDateNaissance(LocalDate.of(1990, 5, 12));
        inputDto.setType(TypePatient.SANS_SOINS);
        inputDto.setNumeroChambre(301);

        // Conversion DTO -> Entity via la méthode statique du mapper
        Patient mappedPatient = PatientMapper.toEntity(inputDto, soignantRepository);

        // Patient sauvegardé simulé
        Patient savedPatient = new Patient();
        savedPatient.setId(1L);
        savedPatient.setNom("Durand");
        savedPatient.setPrenom("Paul");
        savedPatient.setDateNaissance(LocalDate.of(1990, 5, 12));
        savedPatient.setType(TypePatient.SANS_SOINS);
        savedPatient.setNumeroChambre(301);

        // --- MOCK DES COMPORTEMENTS ---
        when(patientRepository.save(mappedPatient)).thenReturn(savedPatient);


        // --- ACT --- Appel de la méthode à tester
        PatientDTO result = patientService.createPatient(inputDto);

        // --- ASSERT --- Vérification du résultat
        assertEquals("Durand", result.getNom());
        assertEquals("Paul", result.getPrenom());
        assertEquals(301, result.getNumeroChambre());
        assertNotNull(result.getId());

        // Vérifie que les méthodes simulées ont bien été appelées
        verify(patientRepository).save(mappedPatient);
    }

    // Vérifie que la méthode findById retourne bien un patient existant
    @Test
    void testFindByIdWhenPatientExists(){
        // --- ARRANGE --- Création d'un patient fictif
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setNom("Chebel");
        patient.setPrenom("Anne");
        patient.setDateNaissance(LocalDate.of(1988, 12, 31));
        patient.setType(TypePatient.SANS_SOINS);
        patient.setNumeroChambre(404);
        patient.setNiveauAutonomie("autonome");
        patient.setToiletteAssistee(true);
        patient.setAideHabillage(false);
        patient.setAideRepas(false);

        // Simule la recherche dans le repository
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTO dto = new PatientDTO();
        dto.setNom("Anne");

        // --- ACT --- Appel de la méthode
        Optional<PatientDTO> result = patientService.findById(1L);

        // --- ASSERT --- Vérification
        assertTrue(result.isPresent());
        assertEquals("Chebel", result.get().getNom());
        verify(patientRepository, times(1)).findById(1L);
    }

    // Vérifie que la méthode findById retourne Optional.empty() si l'ID n'existe pas
    @Test
    void testFindByIdWhenPatientDoesNotExist() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<PatientDTO> result = patientService.findById(99L);

        assertTrue(result.isEmpty());
        verify(patientRepository, times(1)).findById(99L);
    }


    // Vérifie que la suppression d'un patient appelle bien le repository
    @Test
    void testDeletePatient() {
        Long patientId = 2L;

        doNothing().when(patientRepository).deleteById(patientId);

        patientService.deletePatient(patientId);

        verify(patientRepository, times(1)).deleteById(patientId);
    }

    // Vérifie que la mise à jour fonctionne quand le patient existe
    @Test
    void testUpdatePatient_whenExists_shouldUpdateAndReturnDTO() {
        // ARRANGE
        Long id = 1L;
        Patient existingPatient = new Patient();
        existingPatient.setId(id);
        existingPatient.setNom("Ancien");
        existingPatient.setPrenom("Nom");

        PatientDTO updatedDto = new PatientDTO();
        updatedDto.setNom("Nouveau");
        updatedDto.setPrenom("Prénom");
        updatedDto.setNumeroChambre(205);
        updatedDto.setType(TypePatient.SANS_SOINS);

        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        PatientDTO result = patientService.updatePatient(id, updatedDto);

        // ASSERT
        assertEquals("Nouveau", result.getNom());
        assertEquals("Prénom", result.getPrenom());
        assertEquals(205, result.getNumeroChambre());
        verify(patientRepository).save(any(Patient.class));
    }

    // Vérifie que la mise à jour d’un patient inexistant lève une exception 404
    @Test
    void testUpdatePatient_whenNotFound_shouldThrowException() {
        // ARRANGE
        Long id = 99L;
        PatientDTO dto = new PatientDTO();
        dto.setNom("Test");

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        // ACT + ASSERT
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            patientService.updatePatient(id, dto);
        });

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        assertEquals("Patient non trouvé avec l'ID: " + id, ex.getReason());
    }

    // Vérifie que la méthode findAll retourne une liste de patients bien mappée
    @Test
    void testFindAll_shouldReturnListOfPatientDTO() {
        Patient p1 = new Patient();
        p1.setId(1L);
        p1.setNom("Paul");

        Patient p2 = new Patient();
        p2.setId(2L);
        p2.setNom("Marie");

        when(patientRepository.findAll()).thenReturn(List.of(p1, p2));

        List<PatientDTO> result = patientService.findAll();

        assertEquals(2, result.size());
        assertEquals("Paul", result.get(0).getNom());
        assertEquals("Marie", result.get(1).getNom());
        verify(patientRepository).findAll();
    }

    // Vérifie que tous les champs sont bien mis à jour lors de la mise à jour d’un patient existant
    @Test
    void testUpdatePatient_whenExists_shouldUpdateSuccessfully() {
        // --- ARRANGE --- Création d’un patient existant
        Long id = 1L;
        Patient existing = new Patient();
        existing.setId(id);
        existing.setNom("AncienNom");
        existing.setPrenom("AncienPrenom");
        existing.setDateNaissance(LocalDate.of(1990, 1, 1));
        existing.setType(TypePatient.SANS_SOINS);
        existing.setNumeroChambre(100);

        // DTO contenant les nouvelles valeurs
        PatientDTO dto = new PatientDTO();
        dto.setNom("NouveauNom");
        dto.setPrenom("NouveauPrenom");
        dto.setDateNaissance(LocalDate.of(1991, 2, 2));
        dto.setType(TypePatient.ALZHEIMER);
        dto.setNumeroChambre(200);

        // Patient mis à jour
        Patient updatedPatient = new Patient();
        updatedPatient.setId(id);
        updatedPatient.setNom(dto.getNom());
        updatedPatient.setPrenom(dto.getPrenom());
        updatedPatient.setDateNaissance(dto.getDateNaissance());
        updatedPatient.setType(dto.getType());
        updatedPatient.setNumeroChambre(dto.getNumeroChambre());

        // --- MOCK --- les comportements
        when(patientRepository.findById(id)).thenReturn(Optional.of(existing));
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);

        // --- ACT ---
        PatientDTO result = patientService.updatePatient(id, dto);

        // --- ASSERT ---
        assertNotNull(result);
        assertEquals("NouveauNom", result.getNom());
        assertEquals("NouveauPrenom", result.getPrenom());
        assertEquals(LocalDate.of(1991, 2, 2), result.getDateNaissance());
        assertEquals(TypePatient.ALZHEIMER, result.getType());
        assertEquals(200, result.getNumeroChambre());

        verify(patientRepository).findById(id);
        verify(patientRepository).save(any(Patient.class));
    }






}
