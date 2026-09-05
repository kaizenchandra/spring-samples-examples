package com.synechisveltiosi.springbootkaizen.concept.ioc;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * This class demonstrates Spring Inversion of Control (IoC) and Dependency Injection (DI).
 * IoC is a design principle where the control over object creation and lifecycle
 * is transferred from the application to the Spring container.
 */

@Component
public class SpringIOCDemo {

    /**
     * Interface defining payment processing contract.
     * In IoC, interfaces are used to define loose coupling between components.
     */
    interface PaymentProcessor {
        boolean processPayment(double amount);
    }

    /**
     * @Component annotation marks this class as a Spring-managed bean.
     * Spring container will create and manage instances of this class.
     */
    @Component
    static class CreditCardProcessor implements PaymentProcessor {
        @Override
        public boolean processPayment(double amount) {
            return amount > 0 && amount < 10000;
        }
    }

    /**
     * @Service annotation is a specialized @Component for service layer.
     * This class demonstrates constructor-based dependency injection.
     */
    @Service
    static class PaymentService {
        private final PaymentProcessor paymentProcessor;

        /**
         * @Autowired enables automatic dependency injection.
         * Spring container will provide the PaymentProcessor implementation.
         */
        @Autowired
        public PaymentService(PaymentProcessor paymentProcessor) {
            this.paymentProcessor = paymentProcessor;
        }

        public boolean makePayment(double amount) {
            return paymentProcessor.processPayment(amount);
        }
    }

    /**
     * Demo class showing how Spring manages the complete object graph.
     * Spring automatically injects all required dependencies.
     */
    @Component
    static class PaymentDemo {
        private final PaymentService paymentService;

        /**
         * Constructor injection ensures all required dependencies are available
         * when the object is created.
         */
        @Autowired
        public PaymentDemo(PaymentService paymentService) {
            this.paymentService = paymentService;
        }

        @PostConstruct
        public void postConstruct() {
            if(Objects.nonNull(paymentService)) {
                System.out.println("PaymentService dependency is injected");
            }else {
                System.out.println("PaymentService dependency is null");
            }
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("PaymentDemo is being destroyed");
        }


    }
}
