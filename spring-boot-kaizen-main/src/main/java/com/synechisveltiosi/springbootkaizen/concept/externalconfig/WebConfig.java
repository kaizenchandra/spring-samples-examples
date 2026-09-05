package com.synechisveltiosi.springbootkaizen.concept.externalconfig;

import jakarta.persistence.PreUpdate;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public ServletContextInitializer initializer() {
        return servletContext -> servletContext.setInitParameter("spring.profiles.active", "dev");
    }
}
