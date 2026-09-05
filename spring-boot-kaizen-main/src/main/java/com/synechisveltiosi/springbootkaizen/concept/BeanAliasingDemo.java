package com.synechisveltiosi.springbootkaizen.concept;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Component
public class BeanAliasingDemo {

    interface MessageService {
        String getMessage();
    }

    @Component
    static class EmailMessageService implements MessageService {
        @Override
        public String getMessage() {
            return "Email Message Service";
        }
    }

    @Configuration
    static class MessageConfig {
        @Bean(name = {"messageService", "emailService", "primaryMessageService"})
        public MessageService messageService() {
            return new EmailMessageService();
        }
    }

    @Component
     class MessageClient {
        private final MessageService messageService;

        public MessageClient(MessageService messageService) {
            this.messageService = messageService;
        }

        public String showMessage() {
            return messageService.getMessage();
        }

        @PostConstruct
        public void init(){
            System.out.println("MessageClient init");
            System.out.println(showMessage());
        }
    }
}
