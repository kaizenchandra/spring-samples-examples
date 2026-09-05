package com.synechisveltiosi.springsecurityjwtlogin.controller;

import com.synechisveltiosi.springsecurityjwtlogin.model.AuthRequest;
import com.synechisveltiosi.springsecurityjwtlogin.model.AuthResponse;
import com.synechisveltiosi.springsecurityjwtlogin.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> createAuthToken(@RequestBody AuthRequest request) throws Exception {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            final String username = userDetailsService.loadUserByUsername(request.username()).getUsername();
            return ResponseEntity.ok(
                    new AuthResponse(
                            jwtUtil.generateAccessToken(username),
                            jwtUtil.generateRefreshToken(username)));
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (jwtUtil.validateToken(refreshToken)) {
            String username = jwtUtil.extractUsername(refreshToken);
            return ResponseEntity.ok(new AuthResponse(
                    jwtUtil.generateAccessToken(username),
                    jwtUtil.generateRefreshToken(username)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
    }
}
