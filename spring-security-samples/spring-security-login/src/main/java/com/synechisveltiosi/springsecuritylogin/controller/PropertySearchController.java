package com.synechisveltiosi.springsecuritylogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PropertySearchController {

    @GetMapping("/search")
    public String propertySearch() {
        return "Welcome to Property Search Page ";
    }

}
