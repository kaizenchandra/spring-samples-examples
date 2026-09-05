package com.synechisveltiosi.formlogin.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String greetUser(){
        return "Welcome to "+ SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
