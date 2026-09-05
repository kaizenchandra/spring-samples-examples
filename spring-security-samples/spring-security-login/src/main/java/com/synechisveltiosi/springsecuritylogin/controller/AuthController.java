package com.synechisveltiosi.springsecuritylogin.controller;

import com.synechisveltiosi.springsecuritylogin.model.AuthRequest;
import com.synechisveltiosi.springsecuritylogin.model.AuthResponse;
import com.synechisveltiosi.springsecuritylogin.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTService jwtService;


    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
        String accessToken = jwtService.generateToken(userDetails);
        return ResponseEntity.ok().body(new AuthResponse(accessToken));
    }

}
