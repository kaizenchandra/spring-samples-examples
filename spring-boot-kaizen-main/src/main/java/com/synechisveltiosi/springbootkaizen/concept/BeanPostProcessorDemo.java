package com.synechisveltiosi.springbootkaizen.concept;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
public class BeanPostProcessorDemo {

    @Component
    static class CustomBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof DemoService) {
                log.info("Before Initialization of DemoService: {}", beanName);
                ((DemoService) bean).setMessage("Modified in BeforeInitialization");
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof DemoService) {
                log.info("After Initialization of DemoService: {}", beanName);
                ((DemoService) bean).setMessage("Modified in AfterInitialization");
            }
            return bean;
        }
    }

    @Service
    static class DemoService {
        private String message = "Original Message";

        @PostConstruct
        public void init() {
            log.info("DemoService initialized with message: {}", message);
        }

        @PreDestroy
        public void destroy() {
            log.info("DemoService being destroyed with message: {}", message);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
