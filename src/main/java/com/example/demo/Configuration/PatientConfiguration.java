package com.example.demo.Configuration;

import com.example.demo.Repository.PatientRepository;
import com.example.demo.Service.PatientService;
import com.example.demo.ServiceImpl.PatientServiceImpl;
import org.springframework.context.annotation.Bean;

public class PatientConfiguration {
    @Bean
    public PatientService patientService(PatientRepository patientRepository){
        return new PatientServiceImpl(patientRepository);
    }
}
