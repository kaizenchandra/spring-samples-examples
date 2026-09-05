package com.synechisveltiosi.springsecuritylogin.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessToken.expiration}")
    private long accessTokenExpireInSeconds;


    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), accessTokenExpireInSeconds);
    }

    private String createToken(Map<String, Object> claims, String username, long accessTokenExpireInSeconds) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpireInSeconds))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractClaim(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractClaim(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key()).build()
                .parseClaimsJws(token).getBody();
    }

    public Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


}
