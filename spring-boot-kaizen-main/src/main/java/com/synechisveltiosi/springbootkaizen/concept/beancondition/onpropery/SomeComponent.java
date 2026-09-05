package com.synechisveltiosi.springbootkaizen.concept.beancondition.onpropery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "notification.service.enable", havingValue = "true")
public class SomeComponent implements CommandLineRunner {
    private final Service service;

    public SomeComponent(Service service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------------------------");
        System.out.println("SomeComponent is running");
        System.out.println("Service is: " + service.doSomething() + "\t");
    }

}
