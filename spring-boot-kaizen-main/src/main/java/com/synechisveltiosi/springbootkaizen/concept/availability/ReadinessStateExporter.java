package com.synechisveltiosi.springbootkaizen.concept.availability;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReadinessStateExporter {

    @EventListener
    public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {
        System.out.println("Readiness state changed to " + event.getState());
        switch (event.getState()) {
            case ACCEPTING_TRAFFIC:
                System.out.println("Application is ready to receive traffic");
                break;
            case REFUSING_TRAFFIC:
                System.out.println("Application is not ready to receive traffic");
                break;
            default:
        }
    }
}
