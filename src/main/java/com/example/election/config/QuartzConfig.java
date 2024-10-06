package com.example.election.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
public class QuartzConfig {

//    @Bean
//    @QuartzDataSource
//    public DataSource quartzDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/quartz_job_db");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("pgql");
//        return dataSource;
//    }

    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return new SchedulerFactoryBeanCustomizer() {
            @Override
            public void customize(SchedulerFactoryBean bean) {
                bean.setQuartzProperties(createQuartzProperties());
            }
        };
    }

    private Properties createQuartzProperties() {
        Properties props = new Properties();
        props.put("spring.quartz.properties.org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
        return props;
    }
}