package com.synechisveltiosi.springbootkaizen.concept.beancondition.onclass;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceNameAutoConfiguration implements CommandLineRunner {

    @Bean
    @ConditionalOnClass(RestTemplate.class)
    public CustomRestTemplate restTemplateService() {
        return new CustomRestTemplate();
    }

    @Bean
    @ConditionalOnClass(JdbcTemplate.class)
    public CustomJdbcTemplate jdbcTemplateService() {
        return new CustomJdbcTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testing @ConditionalOnClass configuration:");
        try {
            Class.forName("org.springframework.web.client.RestTemplate");
            System.out.println("RestTemplate is in classpath");
        } catch (ClassNotFoundException e) {
            System.out.println("RestTemplate is not in classpath");
        }
        try {
            Class.forName("org.springframework.jdbc.core.JdbcTemplate");
            System.out.println("JdbcTemplate is in classpath");
        } catch (ClassNotFoundException e) {
            System.out.println("JdbcTemplate is not in classpath");
        }
    }
}



