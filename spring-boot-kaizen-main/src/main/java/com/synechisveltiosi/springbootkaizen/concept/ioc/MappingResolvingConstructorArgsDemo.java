package com.synechisveltiosi.springbootkaizen.concept.ioc;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

@Component
public class MappingResolvingConstructorArgsDemo {

    @Component
    class ConstructorPropertiesDemo {
        private final String message;
        private final int value;

        @ConstructorProperties({"message", "value"})
        public ConstructorPropertiesDemo(@Value("${person.name: John}") String message, @Value("${person.age:32}") int value) {
            this.message = message;
            this.value = value;
        }

        @PostConstruct
        public void printDetails() {
            System.out.println("Name: " + message + ", Age: " + value);
        }
    }
}
