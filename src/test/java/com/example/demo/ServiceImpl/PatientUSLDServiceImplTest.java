// PatientUSLDServiceImplTest.java
package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientUSLDDTO;
import com.example.demo.Entity.PatientUSLD;
import com.example.demo.Mapper.PatientMapper;
import com.example.demo.Repository.PatientRepository;
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
public class PatientUSLDServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientUSLDServiceImpl patientUSLDService;

    @Test
    void testCreatePatientUSLD() {
        PatientUSLDDTO inputDto = new PatientUSLDDTO();
        inputDto.setNom("Dupont");
        inputDto.setPrenom("Claire");
        inputDto.setDateNaissance(LocalDate.of(1985, 3, 15));
        inputDto.setNumeroChambre(101);
        inputDto.setNiveauAutonomie("moyen");
        inputDto.setToiletteAssistee(true);
        inputDto.setAideHabillage(true);
        inputDto.setAideRepas(false);

        PatientUSLD entity = PatientMapper.toEntity(inputDto);
        PatientUSLD savedEntity = new PatientUSLD();
        savedEntity.setId(1L);
        savedEntity.setNom("Dupont");
        savedEntity.setPrenom("Claire");
        savedEntity.setDateNaissance(LocalDate.of(1985, 3, 15));

        when(patientRepository.save(any(PatientUSLD.class))).thenReturn(savedEntity);

        PatientUSLDDTO result = patientUSLDService.create(inputDto);

        assertNotNull(result);
        assertEquals("Dupont", result.getNom());
        assertEquals("Claire", result.getPrenom());
        verify(patientRepository).save(any(PatientUSLD.class));
    }

    @Test
    void testFindById_whenExists_shouldReturnDTO() {
        Long id = 1L;
        PatientUSLD entity = new PatientUSLD();
        entity.setId(id);
        entity.setNom("Test");

        when(patientRepository.findById(id)).thenReturn(Optional.of(entity));

        PatientUSLDDTO result = patientUSLDService.findById(id);

        assertNotNull(result);
        assertEquals("Test", result.getNom());
    }

    @Test
    void testFindById_whenNotFound_shouldThrowException() {
        Long id = 99L;
        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> patientUSLDService.findById(id));
    }

    @Test
    void testDelete_shouldCallRepository() {
        Long id = 2L;
        doNothing().when(patientRepository).deleteById(id);

        patientUSLDService.delete(id);

        verify(patientRepository).deleteById(id);
    }

    @Test
    void testFindAll_shouldReturnOnlyUSLD() {
        PatientUSLD p1 = new PatientUSLD();
        p1.setId(1L);
        p1.setNom("USLD1");

        PatientUSLD p2 = new PatientUSLD();
        p2.setId(2L);
        p2.setNom("USLD2");

        when(patientRepository.findAll()).thenReturn(List.of(p1, p2));

        List<PatientUSLDDTO> results = patientUSLDService.findAll();

        assertEquals(2, results.size());
    }

    @Test
    void testUpdate_shouldReturnUpdatedDTO() {
        Long id = 1L;
        PatientUSLD existing = new PatientUSLD();
        existing.setId(id);
        existing.setNom("AncienNom");

        PatientUSLDDTO dto = new PatientUSLDDTO();
        dto.setNom("NouveauNom");

        when(patientRepository.findById(id)).thenReturn(Optional.of(existing));
        when(patientRepository.save(any(PatientUSLD.class))).thenAnswer(inv -> inv.getArgument(0));

        PatientUSLDDTO result = patientUSLDService.update(id, dto);

        assertEquals("NouveauNom", result.getNom());
    }
}
