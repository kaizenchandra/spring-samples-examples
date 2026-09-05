package com.synechisveltiosi.springbootkaizen.concept.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class DependencyInjection {

    interface MessageService {
        String getMessage();
    }

    @Service
    static class EmailService implements MessageService {
        @Override
        public String getMessage() {
            return "Email Service Message";
        }
    }

    @Component
    static class ConstructorInjectionDemo {
        private final MessageService messageService;

        @Autowired
        public ConstructorInjectionDemo(MessageService messageService) {
            this.messageService = messageService;
        }

        public String showMessage() {
            return messageService.getMessage();
        }
    }

    @Component
    static class SetterInjectionDemo {
        private MessageService messageService;

        @Autowired
        public void setMessageService(MessageService messageService) {
            this.messageService = messageService;
        }

        public String showMessage() {
            return messageService.getMessage();
        }
    }

    @Component
    static class FieldInjectionDemo {
        @Autowired
        private MessageService messageService;

        public String showMessage() {
            return messageService.getMessage();
        }
    }
}
