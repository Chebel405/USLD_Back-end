package com.example.demo.Controller;

import com.example.demo.Dto.SoinDTO;
import com.example.demo.Service.SoinService;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.SetOfIntegerSyntax;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/soins")
public class SoinController {

    private final SoinService soinService;

    public SoinController(SoinService soinService){
        this.soinService = soinService;
    }

    @GetMapping
    public List<SoinDTO> findAll(){
        return soinService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<SoinDTO> findById(@PathVariable Long id){
        return soinService.findById(id);
    }

    @PostMapping("")
    public SoinDTO createSoin(@RequestBody SoinDTO soinDTO){
        return soinService.createSoin(soinDTO);
    }

    @PutMapping("/{id}")
    public SoinDTO updateSoin(@PathVariable Long id, @RequestBody SoinDTO soinDTO){
        return soinService.updateSoin(id, soinDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSoin(@PathVariable Long id){
        soinService.deleteSoin(id);
    }

}
