package com.synechisveltiosi.springsecurityjwtlogin.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleInvalidCredentials(BadCredentialsException ex) {
        ProblemDetail problemDetail = problemDetailUnAuthorized(ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUsernameNotFound(UsernameNotFoundException ex) {
        ProblemDetail problemDetail = problemDetailUnAuthorized(ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ProblemDetail> handleExpiredJWTException(ExpiredJwtException ex) {
        ProblemDetail problemDetail = problemDetailUnAuthorized(ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }


    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ProblemDetail> handleMalformedJwtException(MalformedJwtException ex) {
        ProblemDetail problemDetail = problemDetailUnAuthorized(ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ProblemDetail> handleJwtException(JwtException ex) {
        ProblemDetail problemDetail = problemDetailUnAuthorized(ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAccessDeniedException(AccessDeniedException ex) {
        ProblemDetail problemDetail = problemDetailUnAuthorized(ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ProblemDetail> handleAuthenticationException(AuthenticationException ex) {
        ProblemDetail problemDetail = problemDetailUnAuthorized(ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ProblemDetail> handleException(Exception ex) {
        ProblemDetail problemDetail = problemDetailUnAuthorized(ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }


    private ProblemDetail problemDetailUnAuthorized(Exception ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
    }




}
