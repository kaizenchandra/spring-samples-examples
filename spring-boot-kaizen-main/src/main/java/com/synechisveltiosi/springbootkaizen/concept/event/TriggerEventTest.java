package com.synechisveltiosi.springbootkaizen.concept.event;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TriggerEventTest implements CommandLineRunner {
    private final EventPublisher eventPublisher;
    public TriggerEventTest(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    @Override
    public void run(String... args) throws Exception {
        eventPublisher.publishEvent(new CustomEvent(this, "Hello World!"));
    }
}
