package com.synechisveltiosi.springsecuritylogin.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerDashboardController {

    //@PreAuthorize("hasAnyRole('MANAGER')")
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to Manager Dashboard Page "+ SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
