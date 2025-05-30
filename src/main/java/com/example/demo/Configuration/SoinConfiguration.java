package com.example.demo.Configuration;

import com.example.demo.Repository.SoinRepository;
import com.example.demo.Service.SoinService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoinConfiguration {
    @Bean
    public SoinService soinService(SoinRepository soinRepository)
}
