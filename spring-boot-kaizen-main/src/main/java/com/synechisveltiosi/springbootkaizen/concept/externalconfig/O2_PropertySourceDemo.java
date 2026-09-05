package com.synechisveltiosi.springbootkaizen.concept.externalconfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:custom.properties")
public class O2_PropertySourceDemo implements CommandLineRunner {

    private final Environment environment;

    public O2_PropertySourceDemo(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("PropertySourceDemo → " + environment.getProperty("app.title"));
        System.out.println("PropertySourceDemo → " + environment.getProperty("app.version"));
    }
}
