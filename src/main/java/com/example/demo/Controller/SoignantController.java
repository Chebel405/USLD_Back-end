package com.example.demo.Controller;

import com.example.demo.Dto.SoignantDTO;
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

    @GetMapping
    public List<SoignantDTO> findAll(){
        return soignantService.findAll();}


    @GetMapping("/{id}")
    public Optional<SoignantDTO> findById(@PathVariable Long id){
        return soignantService.findById(id);}

    @PostMapping("")
    public SoignantDTO createSoignant(@RequestBody SoignantDTO soignantDTO){
        return soignantService.createSoignant(soignantDTO);
    }

    @PutMapping("/{id}")
    public SoignantDTO updateSoignant(@PathVariable Long id, @RequestBody SoignantDTO soignantDTO){
        return soignantService.updateSoignant(id, soignantDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSoignant(@PathVariable Long id){
        soignantService.deleteSoignant(id);
    }
}