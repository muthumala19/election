package com.example.election.config;

import com.example.election.utils.quartz.job_factory.AutowiringSpringBeanJobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobFactoryConfig {

    @Bean
    public AutowiringSpringBeanJobFactory jobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }
}