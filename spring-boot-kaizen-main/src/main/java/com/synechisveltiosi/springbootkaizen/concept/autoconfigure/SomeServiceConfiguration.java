package com.synechisveltiosi.springbootkaizen.concept.autoconfigure;

import com.synechisveltiosi.springbootkaizen.concept.beancondition.onpropery.DefaultService;
import com.synechisveltiosi.springbootkaizen.concept.beancondition.onpropery.Service;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Service.class)
public class SomeServiceConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Service someService() {
        return new DefaultService();
    }

    @Bean
    @ConditionalOnResource(resources = "classpath:config/app-config.yml")
    public String resourceBasedBean() {
        return "Loaded because app-config.yml is present!";
    }
    @Bean
    @ConditionalOnProperty(
            name = "notification.service.enable",
            havingValue = "true",
            matchIfMissing = false
    )
    public String featureBean() {
        return "Feature is enabled!";
    }

}

