package com.example.demo.ServiceImpl;

import com.example.demo.Dto.PatientSansSoinDTO;
import com.example.demo.Entity.PatientSansSoin;
import com.example.demo.Repository.PatientSansSoinRepository;
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
public class PatientSansSoinServiceImplTest {

    @Mock
    private PatientSansSoinRepository patientSansSoinRepository;

    @InjectMocks
    private PatientSansSoinServiceImpl patientSansSoinService;

    @Test
    void testCreatePatientSansSoin() {
        PatientSansSoinDTO inputDto = new PatientSansSoinDTO();
        inputDto.setNom("Martin");
        inputDto.setPrenom("Julie");
        inputDto.setDateNaissance(LocalDate.of(1975, 4, 18));

        PatientSansSoin savedEntity = new PatientSansSoin();
        savedEntity.setId(1L);
        savedEntity.setNom("Martin");
        savedEntity.setPrenom("Julie");
        savedEntity.setDateNaissance(LocalDate.of(1975, 4, 18));

        when(patientSansSoinRepository.save(any(PatientSansSoin.class))).thenReturn(savedEntity);

        PatientSansSoinDTO result = patientSansSoinService.create(inputDto);

        assertNotNull(result);
        assertEquals("Martin", result.getNom());
        assertEquals("Julie", result.getPrenom());

        verify(patientSansSoinRepository).save(any(PatientSansSoin.class));
    }

    @Test
    void testFindByIdWhenExists() {
        PatientSansSoin entity = new PatientSansSoin();
        entity.setId(1L);
        entity.setNom("Lemoine");

        when(patientSansSoinRepository.findById(1L)).thenReturn(Optional.of(entity));

        PatientSansSoinDTO result = patientSansSoinService.findById(1L);

        assertNotNull(result);
        assertEquals("Lemoine", result.getNom());
    }

    @Test
    void testFindByIdWhenNotFound() {
        when(patientSansSoinRepository.findById(77L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            patientSansSoinService.findById(77L);
        });

        assertEquals("Patient Sans Soin non trouvé", exception.getMessage());
    }

    @Test
    void testFindAll() {
        PatientSansSoin p1 = new PatientSansSoin();
        p1.setId(1L);
        p1.setNom("Sophie");

        PatientSansSoin p2 = new PatientSansSoin();
        p2.setId(2L);
        p2.setNom("Claire");

        when(patientSansSoinRepository.findAll()).thenReturn(List.of(p1, p2));

        List<PatientSansSoinDTO> result = patientSansSoinService.findAll();

        assertEquals(2, result.size());
        assertEquals("Sophie", result.get(0).getNom());
        assertEquals("Claire", result.get(1).getNom());
    }

    @Test
    void testUpdatePatient() {
        Long id = 1L;

        PatientSansSoin existing = new PatientSansSoin();
        existing.setId(id);
        existing.setNom("Ancien");

        PatientSansSoinDTO updatedDto = new PatientSansSoinDTO();
        updatedDto.setNom("Nouveau");
        updatedDto.setPrenom("Lucas");

        when(patientSansSoinRepository.findById(id)).thenReturn(Optional.of(existing));
        when(patientSansSoinRepository.save(any(PatientSansSoin.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PatientSansSoinDTO result = patientSansSoinService.update(id, updatedDto);

        assertEquals("Nouveau", result.getNom());
        assertEquals("Lucas", result.getPrenom());
    }

    @Test
    void testDelete() {
        Long id = 5L;

        doNothing().when(patientSansSoinRepository).deleteById(id);

        patientSansSoinService.delete(id);

        verify(patientSansSoinRepository).deleteById(id);
    }
}