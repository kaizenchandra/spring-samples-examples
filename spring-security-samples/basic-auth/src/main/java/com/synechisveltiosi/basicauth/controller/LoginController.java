package com.synechisveltiosi.basicauth.controller;

import com.synechisveltiosi.basicauth.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Authentication> login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        if (authenticationResponse.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
        }
        return ResponseEntity.ok(authenticationResponse);
    }


}
