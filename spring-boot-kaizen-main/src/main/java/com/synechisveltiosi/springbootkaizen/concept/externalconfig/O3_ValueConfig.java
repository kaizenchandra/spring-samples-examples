package com.synechisveltiosi.springbootkaizen.concept.externalconfig;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Data
public class O3_ValueConfig implements CommandLineRunner {

    @Value("${app.title}")
    private String title;

    @Value("${app.version}")
    private String version;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("ValueConfig → " + title);
        System.out.println("ValueConfig → " + version);
    }
}
