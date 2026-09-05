package com.synechisveltiosi.springsecuritylogin.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant")
public class TenantController {

   // @PreAuthorize("hasRole('TENANT')")
    @GetMapping("/profile")
    public String profile(){
        return "Welcome to Tenant Profile Page "+ SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
