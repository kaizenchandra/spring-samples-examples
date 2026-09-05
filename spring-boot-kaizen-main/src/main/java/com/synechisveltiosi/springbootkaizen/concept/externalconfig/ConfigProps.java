package com.synechisveltiosi.springbootkaizen.concept.externalconfig;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class ConfigProps{
    private String title;
    private String version;
}
