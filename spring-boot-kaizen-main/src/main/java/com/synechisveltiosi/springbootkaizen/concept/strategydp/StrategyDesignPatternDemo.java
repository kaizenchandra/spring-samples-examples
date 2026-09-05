package com.synechisveltiosi.springbootkaizen.concept.strategydp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class StrategyDesignPatternDemo {
    
    enum PaymentProvider {
        CREDIT_CARD, PAYPAL, UPI;
    }
    record PaymentResult(String txId, boolean success, String provider, String message) {
    }
    record PaymentRequest(double amount){}
    interface PaymentStrategy {
        PaymentProvider key();

        PaymentResult pay(PaymentRequest paymentRequest);
    }

    @Component
    class CreditCardPaymentStrategy implements PaymentStrategy {
        @Override
        public PaymentProvider key() {
            return PaymentProvider.CREDIT_CARD;
        }

        @Override
        public PaymentResult pay(PaymentRequest paymentRequest) {
            boolean txSuccess = paymentRequest.amount() < 10000;
            return new PaymentResult(UUID.randomUUID().toString(), txSuccess, "credit-card", txSuccess ? "Payment successful" : "Payment failed");
        }

    }

    @Component
    class PayPalPaymentStrategy implements PaymentStrategy {
        @Override
        public PaymentProvider key() {
            return PaymentProvider.PAYPAL;
        }


        @Override
        public PaymentResult pay(PaymentRequest paymentRequest) {
            return new PaymentResult(UUID.randomUUID().toString(), true, "paypal", "Payment successful");
        }
    }

    @Component
    class UpiPaymentStrategy implements PaymentStrategy {
        @Override
        public PaymentProvider key() {
            return PaymentProvider.UPI;
        }

        @Override
        public PaymentResult pay(PaymentRequest paymentRequest) {
            return new PaymentResult(UUID.randomUUID().toString(), true, "upi", "Payment successful");
        }
    }

    @Service
    class PaymentService {
        private final Map<PaymentProvider, PaymentStrategy> strategies;

        public PaymentService(Map<String, PaymentStrategy> strategyMap) {
            this.strategies = strategyMap
                    .values().stream()
                    .collect(Collectors.toMap(
                            PaymentStrategy::key,
                            strategy -> strategy));
        }

        public Map<PaymentProvider, PaymentStrategy> getStrategies() {
            return strategies;
        }

        private PaymentStrategy selectStrategy(PaymentProvider provider) {
            return strategies.get(provider);
        }
    }

    @Controller
    @RequestMapping("/shop")
    class PaymentServiceTestController {
        private final PaymentService paymentService;

        PaymentServiceTestController(PaymentService paymentService) {
            this.paymentService = paymentService;
        }
        @PostMapping("/pay/{type}")
        public ResponseEntity<PaymentResult> pay(@PathVariable("type") String type, @RequestBody PaymentRequest paymentRequest) {
           try {
               PaymentStrategy strategy = paymentService.selectStrategy(PaymentProvider.valueOf(type.toUpperCase()));
               PaymentResult result = strategy.pay(paymentRequest);
               return new ResponseEntity<>(result, HttpStatus.CREATED);
           }catch (Exception e) {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
        }
    }
}
