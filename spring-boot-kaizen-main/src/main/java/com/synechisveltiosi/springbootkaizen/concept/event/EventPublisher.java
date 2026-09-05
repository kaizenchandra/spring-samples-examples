package com.synechisveltiosi.springbootkaizen.concept.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final ApplicationEventPublisher eventPublisher;
    public EventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    public void publishEvent(CustomEvent event) {
        this.eventPublisher.publishEvent(event);
    }
}
