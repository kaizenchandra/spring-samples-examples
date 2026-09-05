package com.synechisveltiosi.springbootkaizen.concept.lazy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {

    private final ServiceB serviceB;

    public ServiceA(@Lazy ServiceB serviceB) {
        this.serviceB = serviceB;
        System.out.println("ServiceA bean is being created...");
    }

    public String callB() {
        return "A calling → " + serviceB.helloFromB();
    }

    public String helloFromA() {
        return "Hello from Service A";
    }
}