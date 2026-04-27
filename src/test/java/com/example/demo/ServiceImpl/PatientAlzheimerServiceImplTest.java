package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientAlzheimerDTO;
import com.example.demo.Entity.PatientAlzheimer;
import com.example.demo.Mapper.PatientMapper;
import com.example.demo.Repository.PatientAlzheimerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientAlzheimerServiceImplTest {

    @Mock
    private PatientAlzheimerRepository patientAlzheimerRepository;

    @InjectMocks
    private PatientAlzheimerServiceImpl patientAlzheimerService;

    @Test
    void testCreatePatientAlzheimer() {
        PatientAlzheimerDTO inputDto = new PatientAlzheimerDTO();
        inputDto.setNom("Chebel");
        inputDto.setPrenom("Antoine");
        inputDto.setDateNaissance(LocalDate.of(1985, 10, 29));
        inputDto.setStadeMaladie("Élevé");
        inputDto.setSuiviPsychologue(true);

        PatientAlzheimer entity = PatientMapper.toEntity(inputDto);

        PatientAlzheimer savedEntity = new PatientAlzheimer();
        savedEntity.setId(1L);
        savedEntity.setNom("Chebel");
        savedEntity.setPrenom("Antoine");
        savedEntity.setDateNaissance(LocalDate.of(1985, 10, 29));
        savedEntity.setStadeMaladie("Élevé");
        savedEntity.setSuiviPsychologue(true);

        when(patientAlzheimerRepository.save(any(PatientAlzheimer.class))).thenReturn(savedEntity);

        PatientAlzheimerDTO result = patientAlzheimerService.create(inputDto);

        assertNotNull(result);
        assertEquals("Chebel", result.getNom());
        assertEquals("Antoine", result.getPrenom());
        assertEquals(LocalDate.of(1985, 10, 29), result.getDateNaissance());
        assertEquals("Élevé", result.getStadeMaladie());
        assertTrue(result.getSuiviPsychologue());

        verify(patientAlzheimerRepository).save(any(PatientAlzheimer.class));
    }

    @Test
    void testFindByIdWhenExists() {
        PatientAlzheimer entity = new PatientAlzheimer();
        entity.setId(1L);
        entity.setNom("Dupont");

        when(patientAlzheimerRepository.findById(1L)).thenReturn(Optional.of(entity));

        PatientAlzheimerDTO result = patientAlzheimerService.findById(1L);

        assertNotNull(result);
        assertEquals("Dupont", result.getNom());
    }

    @Test
    void testFindByIdWhenNotFound() {
        when(patientAlzheimerRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            patientAlzheimerService.findById(99L);
        });

        assertEquals("Patient Alzheimer non trouvé", exception.getMessage());
    }

    @Test
    void testFindAll() {
        PatientAlzheimer p1 = new PatientAlzheimer();
        p1.setId(1L);
        p1.setNom("Alice");

        PatientAlzheimer p2 = new PatientAlzheimer();
        p2.setId(2L);
        p2.setNom("Bob");

        when(patientAlzheimerRepository.findAll()).thenReturn(List.of(p1, p2));

        List<PatientAlzheimerDTO> result = patientAlzheimerService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdatePatient() {
        Long id = 1L;

        PatientAlzheimer existing = new PatientAlzheimer();
        existing.setId(id);
        existing.setNom("Ancien");

        PatientAlzheimerDTO updatedDto = new PatientAlzheimerDTO();
        updatedDto.setNom("Nouveau");
        updatedDto.setStadeMaladie("Modéré");

        when(patientAlzheimerRepository.findById(id)).thenReturn(Optional.of(existing));
        when(patientAlzheimerRepository.save(any(PatientAlzheimer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PatientAlzheimerDTO result = patientAlzheimerService.update(id, updatedDto);

        assertEquals("Nouveau", result.getNom());
        assertEquals("Modéré", result.getStadeMaladie());
    }

    @Test
    void testDelete() {
        Long id = 3L;

        doNothing().when(patientAlzheimerRepository).deleteById(id);

        patientAlzheimerService.delete(id);

        verify(patientAlzheimerRepository).deleteById(id);
    }
}