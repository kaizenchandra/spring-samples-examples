package com.synechisveltiosi.springbootkaizen;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.PreUpdate;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class StaticFactoryMethod {



    static class ConnectionFactory {
        private String url;
        private ConnectionFactory(String url) {
            this.url = url;
        }

        public static ConnectionFactory create(String url) {
            return new ConnectionFactory("url");
        }
    }

    @Configuration
    class AppConfig {
        @Bean
        public ConnectionFactory connectionFactory() {
            return ConnectionFactory.create("jdbc:mysql://localhost:3306/test");
        }
    }

}
