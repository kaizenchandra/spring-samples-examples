package com.synechisveltiosi.springbootkaizen.concept.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
public class ServiceB {

    private final ServiceA serviceA;

    public ServiceB(@Lazy ServiceA serviceA) {
        this.serviceA = serviceA;
        System.out.println("ServiceB bean is being created...");
    }

    public String callA() {
        return "B calling → " + serviceA.helloFromA();
    }

    public String helloFromB() {
        return "Hello from Service B";
    }
}

