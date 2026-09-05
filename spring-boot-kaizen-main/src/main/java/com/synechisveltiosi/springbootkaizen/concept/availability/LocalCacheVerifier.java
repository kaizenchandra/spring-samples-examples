package com.synechisveltiosi.springbootkaizen.concept.availability;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class LocalCacheVerifier {
    private final ApplicationEventPublisher eventPublisher;

    public LocalCacheVerifier(ApplicationEventPublisher publisher) {
        this.eventPublisher = publisher;
    }

    public void checkLocalCache() {
        try {
            verifyLocalCacheState();
        } catch (CacheCompletelyBrokenException ex) {
            AvailabilityChangeEvent.publish(this.eventPublisher, ex, LivenessState.BROKEN);
        }
    }

    private void verifyLocalCacheState() {
        if (Math.random() > 0.5) {
            throw new CacheCompletelyBrokenException("Local cache is broken");
        } else {
            System.out.println("Local cache is OK");
        }
    }
}

class CacheCompletelyBrokenException extends RuntimeException {
    public CacheCompletelyBrokenException(String message) {
        super(message);
    }
}
