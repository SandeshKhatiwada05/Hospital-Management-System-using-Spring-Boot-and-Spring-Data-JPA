package com.sandesh.springDataJpaHospitalManagement.error;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ---------- AUTH / SECURITY ----------

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<APIError> handleUsernameNotFound(UsernameNotFoundException ex) {
        return build("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<APIError> handleAuthentication(AuthenticationException ex) {
        return build("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<APIError> handleAccessDenied(AccessDeniedException ex) {
        return build("Access denied", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<APIError> handleExpiredJwt(ExpiredJwtException ex) {
        return build("JWT token expired", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<APIError> handleJwt(JwtException ex) {
        return build("Invalid JWT token", HttpStatus.UNAUTHORIZED);
    }

    // ---------- VALIDATION / REQUEST ----------

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        return build(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIError> handleIllegalArgument(IllegalArgumentException ex) {
        return build(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // ---------- DATABASE / JPA ----------

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<APIError> handleNoSuchElement(NoSuchElementException ex) {
        return build("Resource not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIError> handleDataIntegrity(DataIntegrityViolationException ex) {
        return build("Database constraint violated", HttpStatus.CONFLICT);
    }

    // ---------- FALLBACK ----------

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleGeneric(Exception ex) {
        return build("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ---------- HELPER ----------

    private ResponseEntity<APIError> build(String message, HttpStatus status) {
        APIError apiError = new APIError(message, status);
        return new ResponseEntity<>(apiError, status);
    }
}
