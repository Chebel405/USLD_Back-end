package com.example.demo.Configuration;

import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.SoignantRepository;
import com.example.demo.Service.SoignantService;
import com.example.demo.ServiceImpl.SoignantServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoignantConfiguration {
    @Bean
    public SoignantService soignantService(SoignantRepository soignantRepository, PatientRepository patientRepository){
        return new SoignantServiceImpl(soignantRepository, patientRepository);
    }
}
