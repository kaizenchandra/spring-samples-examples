package com.synechisveltiosi.springbootkaizen.concept.lazy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class LazyConfigBean {

    @Bean
    @Lazy
    // this bean will be initialized lazily when it is requested for the first time
    // this is the same as @Lazy(true)
    // @Lazy(false) will initialize the bean immediately
    public HeavyBean lazyBean() {
        return new HeavyBean();
    }
}
