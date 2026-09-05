package com.synechisveltiosi.springbootkaizen.concept.lazy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServiceCommandLineRunner implements CommandLineRunner {
    private final ServiceA serviceA;
    private final ServiceB serviceB;

    public ServiceCommandLineRunner(ServiceA serviceA, ServiceB serviceB) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Calling A and B");
        System.out.println(serviceA.callB());
        System.out.println(serviceB.callA());
    }
}
