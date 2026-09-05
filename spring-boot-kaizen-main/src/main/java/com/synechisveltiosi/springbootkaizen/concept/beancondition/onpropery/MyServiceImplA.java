package com.synechisveltiosi.springbootkaizen.concept.beancondition.onpropery;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@org.springframework.stereotype.Service
@ConditionalOnProperty(prefix = "app", name = "service.name", havingValue = "A")
public class MyServiceImplA implements Service{
    @Override
    public String doSomething() {
        return "MyServiceImplA";
    }
}
