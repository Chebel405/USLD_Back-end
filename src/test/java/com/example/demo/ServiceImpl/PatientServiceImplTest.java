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

import java.time.LocalDate;
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

    @Test
    void testDeletePatient() {
        Long patientId = 2L;

        // Simule le comportement du repository sans effet
        doNothing().when(patientRepository).deleteById(patientId);

        // Appel du service
        patientService.deletePatient(patientId);

        // Vérifie que la suppression a bien été invoquée
        verify(patientRepository, times(1)).deleteById(patientId);
    }
}
