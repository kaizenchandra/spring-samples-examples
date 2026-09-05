package com.synechisveltiosi.springbootkaizen.concept.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @EventListener
    public void onEvent(CustomEvent event) {
        System.out.println("Received event: " + event.getMessage());
    }
}
