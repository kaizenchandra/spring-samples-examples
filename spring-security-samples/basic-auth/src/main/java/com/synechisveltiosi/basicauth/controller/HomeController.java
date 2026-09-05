package com.synechisveltiosi.basicauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/unsecured")
    public String unsecured() {
        return "Unsecured";
    }

    @GetMapping("/secured")
    public String index() {
        return "Resource secured for "+getUserName();
    }

    private static String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
