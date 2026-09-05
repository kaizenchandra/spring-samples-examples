package com.synechisveltiosi.springbootkaizen.concept.externalconfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class O4_JavaSystemProperties implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Setting system property programmatically
        System.setProperty("app.system.property", "System Property Value");

        // Reading system properties
        System.out.println("JavaSystemProperties → System Property: " +
                System.getProperty("app.system.property"));
        System.out.println("JavaSystemProperties → Java Home: " +
                System.getProperty("java.home"));
        System.out.println("JavaSystemProperties → OS Name: " +
                System.getProperty("os.name"));
    }
}
