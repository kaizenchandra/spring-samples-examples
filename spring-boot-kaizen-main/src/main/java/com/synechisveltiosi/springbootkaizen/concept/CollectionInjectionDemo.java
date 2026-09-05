package com.synechisveltiosi.springbootkaizen.concept;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CollectionInjectionDemo {
    private final List<NotificationService> notificationServices;
    private final Set<String> supportedChannels;
    private final Map<String, NotificationService> serviceMap;

    @Autowired
    public CollectionInjectionDemo(List<NotificationService> notificationServices,
                                   Set<String> supportedChannels,
                                   Map<String, NotificationService> serviceMap) {
        this.notificationServices = notificationServices;
        this.supportedChannels = supportedChannels;
        this.serviceMap = serviceMap;
    }

    @PostConstruct
    public void postConstruct() {
        if(!notificationServices.isEmpty()) System.err.println("notificationServices is not empty");
        if(!supportedChannels.isEmpty()) System.err.println("supportedChannels is not empty");
        if(!serviceMap.isEmpty()) System.err.println("serviceMap is not empty");
    }

    interface NotificationService {
        String sendNotification(String message);
    }

    @Service("emailNotification")
    static class EmailNotificationService implements NotificationService {
        @Override
        public String sendNotification(String message) {
            return "Email: " + message;
        }
    }

    @Service("smsNotification")
    static class SMSNotificationService implements NotificationService {
        @Override
        public String sendNotification(String message) {
            return "SMS: " + message;
        }
    }

    @Configuration
    static class CollectionConfig {
        @Bean
        public Set<String> supportedChannels() {
            return Set.of("email", "sms", "push");
        }
    }
}
