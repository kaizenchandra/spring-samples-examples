package com.synechisveltiosi.springbootkaizen.concept.externalconfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ConfigPropsDemo implements CommandLineRunner {
    private final ConfigProps configProps;

    public ConfigPropsDemo(ConfigProps configProps) {
        this.configProps = configProps;
        System.out.println("ConfigPropsDemo bean is being created...");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ConfigPropsDemo → " + configProps.getTitle());
        System.out.println("ConfigPropsDemo → " + configProps.getVersion());
    }
}
