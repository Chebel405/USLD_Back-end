package com.example.demo.Controller;

import com.example.demo.Entity.Patient;
import com.example.demo.Entity.Soignant;
import com.example.demo.Service.SoignantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/soignants")
public class SoignantController {
    private final SoignantService soignantService;

    public SoignantController(SoignantService soignantService) {
        this.soignantService = soignantService;
    }

    @GetMapping("")
    public List<Soignant> findAll(){ return soignantService.findAll();}

    @GetMapping("/{id}")
    public Optional<Soignant> findById(@PathVariable Long id){ return soignantService.findById(id);}

    @PostMapping("/")
    public Soignant createSoignant(@RequestBody Soignant soignant){
        return soignantService.createSoignant(soignant);
    }

    @PutMapping("/{id}")
    public Soignant updateSoignant(@PathVariable Long id, @RequestBody Soignant soignant){
        return soignantService.updateSoignant(id, soignant);
    }

    @DeleteMapping("/{id}")
    public void deleteSoignant(@PathVariable Long id){
        soignantService.deleteSoignant(id);
    }
}