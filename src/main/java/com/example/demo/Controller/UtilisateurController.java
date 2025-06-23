package com.example.demo.Controller;

import com.example.demo.Dto.PatientDTO;
import com.example.demo.Dto.UtilisateurDTO;
import com.example.demo.Service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    @GetMapping
    public List<UtilisateurDTO> findAll(){
        return utilisateurService.findAll();}

    @GetMapping("/{id}")
    public Optional<UtilisateurDTO> findById(@PathVariable Long id){
        return utilisateurService.findById(id);}

    @PostMapping("")
    public UtilisateurDTO createPatient(@RequestBody UtilisateurDTO patientDTO){
        return utilisateurService.createUtilisateur(patientDTO);
    }

    @PutMapping("/{id}")
    public UtilisateurDTO updatePatient(@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDTO){
        return utilisateurService.updateUtilisateur(id, utilisateurDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateur(@PathVariable Long id){
        utilisateurService.deleteUtilisateur(id);
    }
}
