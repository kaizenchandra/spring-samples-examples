package com.synechisveltiosi.springsecurityjwtlogin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/welcome")
    public ResponseEntity<?> user() {
        return ResponseEntity.ok("Welcome "+ SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
