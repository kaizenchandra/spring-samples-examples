package com.synechisveltiosi.springsecuritylogin.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerDashboardController {

   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to Owner Dashboard Page "+ SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
