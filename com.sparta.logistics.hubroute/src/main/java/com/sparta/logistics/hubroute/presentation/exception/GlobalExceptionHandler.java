package com.sparta.logistics.hubroute.presentation.exception;

import com.sparta.logistics.hubroute.presentation.exception.exceptions.InvalidAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(com.sparta.logistics.hubroute.presentation.exception.exceptions.ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(com.sparta.logistics.hubroute.presentation.exception.exceptions.ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(com.sparta.logistics.hubroute.presentation.exception.exceptions.DuplicateResourceException.class)
    public ResponseEntity<String> handleDuplicateResourceException(com.sparta.logistics.hubroute.presentation.exception.exceptions.DuplicateResourceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidAccessResourceException.class)
    public ResponseEntity<String> handleInvalidAccessResourceException(InvalidAccessResourceException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
