package com.example.election.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SchedulerDataSourceConfig {
    @Bean
    @ConfigurationProperties("quartz.datasource")
    public DataSourceProperties schedulerDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @QuartzDataSource
    public HikariDataSource schedulerDataSource() {
        return schedulerDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    public PlatformTransactionManager schedulerTransactionManager() {
        final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(schedulerDataSource());
        return transactionManager;
    }
}
