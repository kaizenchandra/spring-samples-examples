package com.synechisveltiosi.formlogin.controller;

import com.synechisveltiosi.formlogin.modal.PasswordChangeRequest;
import com.synechisveltiosi.formlogin.service.GreetingService;
import com.synechisveltiosi.formlogin.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final GreetingService greetingService;
    private final UserService userService;

    @GetMapping("/secured")
    @Secured("ROLE_USER")
    public String secured(){
        return "admin";
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String welcome() {
        return greetingService.greetUser();
    }

    @GetMapping("/profile")
    @RolesAllowed("ROLE_USER")
    public Authentication principle() {
        return  SecurityContextHolder.getContext().getAuthentication();
    }

    @PostMapping("/password-change")
    @Secured("ROLE_USER")
    public String passwordChange(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        return userService.passwordChange(passwordChangeRequest);

    }

}
