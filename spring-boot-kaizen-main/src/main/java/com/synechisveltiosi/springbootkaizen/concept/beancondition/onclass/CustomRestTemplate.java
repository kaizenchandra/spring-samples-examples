package com.synechisveltiosi.springbootkaizen.concept.beancondition.onclass;

import jakarta.annotation.PostConstruct;

public class CustomRestTemplate {
    public CustomRestTemplate(){
        System.out.println("CustomRestTemplate constructor");
    }
    @PostConstruct
    public void init() {
        System.out.println("CustomRestTemplate init");
    }
}