package com.sparta.logistics.auth.presentation.exception;

import com.sparta.logistics.auth.presentation.util.AuthApiResponse;
import com.sparta.logistics.auth.presentation.util.AuthApiResponse.Error;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> runtimeExceptionHandle(RuntimeException exception) {
        log.error("runtimeException Handle= ", exception);
        return AuthApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> exceptionHandle(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidExceptionHandle", e);

        String errorMessage = e.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
            .collect(Collectors.joining(", "));

        return AuthApiResponse.error(HttpStatus.BAD_REQUEST, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exceptionHandle(Exception exception) {
        log.error("exception Handle= ", exception);
        return AuthApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
