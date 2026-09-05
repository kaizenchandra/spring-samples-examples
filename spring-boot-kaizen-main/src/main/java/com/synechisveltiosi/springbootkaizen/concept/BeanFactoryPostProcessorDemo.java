package com.synechisveltiosi.springbootkaizen.concept;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Demonstrates the use of BeanFactoryPostProcessor in Spring.
 * BeanFactoryPostProcessor allows modification of bean definitions before any beans are instantiated.
 * This is useful for changing properties or adding metadata to bean definitions programmatically.
 */
public class BeanFactoryPostProcessorDemo {

    @Component
    public static class ExampleBean {
        private String property = "defaultValue";

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }

    public static class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            var beanDefinition = beanFactory.getBeanDefinition("exampleBean");
            beanDefinition.getPropertyValues().add("property", "modifiedValue");
        }
    }

    @Configuration
    public static class Config {
        @Bean
        public static BeanFactoryPostProcessor customBeanFactoryPostProcessor() {
            return new CustomBeanFactoryPostProcessor();
        }
    }
}
