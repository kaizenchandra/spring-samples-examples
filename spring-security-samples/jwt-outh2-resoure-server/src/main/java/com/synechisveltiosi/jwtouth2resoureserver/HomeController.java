package com.synechisveltiosi.jwtouth2resoureserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping("/unsecured")
    public String home() {
        return "Unsecured";
    }

    @GetMapping
    public String secured() {
        return "Secured";
    }
}
