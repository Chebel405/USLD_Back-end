package com.example.demo.ServiceImpl;

import com.example.demo.Dto.SoignantDTO;
import com.example.demo.Entity.Soignant;
import com.example.demo.Enums.TypeSoignant;
import com.example.demo.Mapper.SoignantMapper;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.SoignantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SoignantServiceImplTest {

    @Mock // Simule le repository des patients
    private SoignantRepository soignantRepository;

    @Mock // Simule le repository des soignants (utilisé par le mapper)
    private PatientRepository patientRepository;

    @InjectMocks // Injecte les mocks dans le service testé
    private SoignantServiceImpl soignantService;


    /**
     * ✅ Test de la méthode createSoignant :
     * Ce test vérifie que lorsqu'on envoie un SoignantDTO valide,
     * le service appelle bien le repository pour sauvegarder un nouvel objet Soignant,
     * et retourne un DTO contenant les données attendues (nom, prénom, id...).
     */

    @Test
    void testCreateSoignant() {
        // --- ARRANGE --- Création de l'entrée DTO
        SoignantDTO inputDto = new SoignantDTO();
        inputDto.setNom("Dupond");
        inputDto.setPrenom("Jean");
        inputDto.setType(TypeSoignant.MEDECIN);


        // Conversion DTO -> Entity via la méthode statique du mapper
        Soignant mappedSoignant = SoignantMapper.toEntity(inputDto, patientRepository);

        // Patient sauvegardé simulé
        Soignant savedSoignant = new Soignant();
        savedSoignant.setId(1L);
        savedSoignant.setNom("Dupond");
        savedSoignant.setPrenom("Jean");
        savedSoignant.setType(TypeSoignant.MEDECIN);

        // --- MOCK DES COMPORTEMENTS ---
        when(soignantRepository.save(mappedSoignant)).thenReturn(savedSoignant);


        // --- ACT --- Appel de la méthode à tester
        SoignantDTO result = soignantService.createSoignant(inputDto);

        // --- ASSERT --- Vérification du résultat
        assertEquals("Dupond", result.getNom());
        assertEquals("Jean", result.getPrenom());
        assertNotNull(result.getId());

        // Vérifie que les méthodes simulées ont bien été appelées
        verify(soignantRepository).save(mappedSoignant);
    }

    /**
     * ✅ Test de la méthode findById (cas où le soignant existe) :
     * Vérifie que le service renvoie bien un DTO quand le soignant est trouvé par son ID.
     */

    @Test
    void testGetSoignantByIdWhenExists(){
        // --- ARRANGE --- Création d'un patient fictif
        Soignant soignant = new Soignant();
        soignant.setId(1L);
        soignant.setNom("Chebel");
        soignant.setPrenom("Anne");
        soignant.setType(TypeSoignant.MEDECIN);


        // Simule la recherche dans le repository
        when(soignantRepository.findById(1L)).thenReturn(Optional.of(soignant));

        SoignantDTO dto = new SoignantDTO();
        dto.setNom("Chebel");

        // --- ACT --- Appel de la méthode
        Optional<SoignantDTO> result = soignantService.findById(1L);

        // --- ASSERT --- Vérification
        assertTrue(result.isPresent());
        assertEquals("Chebel", result.get().getNom());
        verify(soignantRepository, times(1)).findById(1L);
    }

    /**
     * ❌ Test de la méthode findById (cas où le soignant n'existe pas) :
     * Vérifie que le service renvoie un Optional vide si aucun soignant n'est trouvé.
     */
    @Test
    void testGetSoignantByIdWhenNotExists() {
        // --- ARRANGE ---
        Long soignantId = 42L;

        // Simule un repository vide pour cet ID
        when(soignantRepository.findById(soignantId)).thenReturn(Optional.empty());

        Optional<SoignantDTO> result = soignantService.findById(soignantId);

        assertTrue(result.isEmpty());
        verify(soignantRepository, times(1)).findById(soignantId);
    }

    /**
     * ✅ Test de la méthode getAllSoignants :
     * Vérifie que le service retourne la liste des soignants existants mappés en DTO.
     */
    @Test
    void testGetAllSoignants() {
        // --- Objectif : vérifier que le service retourne bien tous les soignants (DTO) à partir du repository ---

        // --- ARRANGE : création de 2 soignants fictifs ---
        Soignant s1 = new Soignant();
        s1.setId(1L);
        s1.setNom("Dupont");
        s1.setPrenom("Alice");
        s1.setType(TypeSoignant.INFIRMIER);

        Soignant s2 = new Soignant();
        s2.setId(2L);
        s2.setNom("Martin");
        s2.setPrenom("Bob");
        s2.setType(TypeSoignant.MEDECIN);

        List<Soignant> soignants = List.of(s1, s2);

        // --- MOCK : le repository renvoie la liste lorsqu'on appelle findAllWithPatients() ---
        when(soignantRepository.findAllWithPatients()).thenReturn(soignants);

        // --- ACT ---
        List<SoignantDTO> result = soignantService.findAll();

        // --- ASSERT ---
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Dupont", result.get(0).getNom());
        assertEquals("Martin", result.get(1).getNom());

        // --- Vérifie que la bonne méthode du repository a bien été appelée ---
        verify(soignantRepository, times(1)).findAllWithPatients();
    }

    /**
     * Test de la méthode updateSoignant.
     * Vérifie que les données d'un soignant sont bien mises à jour
     * lorsqu'un ID valide est fourni et que le soignant existe.
     */
    @Test
    void testUpdateSoignant() {
        // --- ARRANGE ---
        Long id = 1L;

        SoignantDTO inputDto = new SoignantDTO();
        inputDto.setNom("Misra");
        inputDto.setPrenom("Sophie");
        inputDto.setType(TypeSoignant.INFIRMIER);

        Soignant existing = new Soignant();
        existing.setId(id);
        existing.setNom("AncienNom");
        existing.setPrenom("AncienPrenom");
        existing.setType(TypeSoignant.MEDECIN);

        when(soignantRepository.findById(id)).thenReturn(Optional.of(existing));
        when(soignantRepository.save(any(Soignant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // --- ACT ---
        SoignantDTO result = soignantService.updateSoignant(id, inputDto);

        // --- ASSERT ---
        assertNotNull(result);
        assertEquals("Misra", result.getNom());
        assertEquals("Sophie", result.getPrenom());
        assertEquals(TypeSoignant.INFIRMIER, result.getType());
        verify(soignantRepository).save(any(Soignant.class));
    }

    /**
     * Test de la méthode deleteSoignant.
     * Vérifie que le repository est bien appelé pour supprimer un soignant avec un ID existant.
     */
    @Test
    void testDeleteSoignant() {
        // --- ARRANGE ---
        Long id = 1L;

        doNothing().when(soignantRepository).deleteById(id);

        // --- ACT ---
        soignantService.deleteSoignant(id);

        // --- ASSERT ---
        verify(soignantRepository, times(1)).deleteById(id);
    }







}
