package com.synechisveltiosi.springbootkaizen.concept.ioc;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app.config")
public class AppConfig {
    private final String name;
    private final int port;

    public AppConfig(String name, int port) {
        this.name = name;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    @PostConstruct
    public void init() {
        System.out.println("NAME: " + name + " PORT: " + port );
    }
}
