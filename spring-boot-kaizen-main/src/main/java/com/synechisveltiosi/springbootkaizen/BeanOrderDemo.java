package com.synechisveltiosi.springbootkaizen;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Configuration
public class BeanOrderDemo {

    interface MessageProcessor {
        String process();
    }

    @Component
    @Order(3)
    static class LowPriorityProcessor implements MessageProcessor {
        @Override
        public String process() {
            return "Low Priority Process";
        }
    }

    @Component
    @Order(1)
    static class HighPriorityProcessor implements MessageProcessor {
        @Override
        public String process() {
            return "High Priority Process";
        }
    }

    @Component
    @Order(2)
    static class MediumPriorityProcessor implements MessageProcessor {
        @Override
        public String process() {
            return "Medium Priority Process";
        }
    }

    @Component
    static class ProcessorService {
        private final List<MessageProcessor> processors;

        @Autowired
        public ProcessorService(List<MessageProcessor> processors) {
            this.processors = processors;
        }

        public void processAll() {
            processors.forEach(processor ->
                    System.out.println(processor.process()));
        }
    }
}
