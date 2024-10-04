package com.example.election.config;

import com.example.election.mappers.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    public Mapper mapper() {
        return new Mapper();
    }

}
