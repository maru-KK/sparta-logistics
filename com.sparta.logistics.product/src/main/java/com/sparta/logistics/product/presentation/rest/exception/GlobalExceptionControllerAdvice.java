package com.sparta.logistics.product.presentation.rest.exception;

import com.sparta.logistics.product.presentation.rest.exception.exceptions.DataNotFoundException;
import com.sparta.logistics.product.presentation.rest.exception.exceptions.InvalidAccessResourceException;
import com.sparta.logistics.product.presentation.rest.util.ApiResponse;
import com.sparta.logistics.product.presentation.rest.util.ApiResponse.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Error> exceptionHandle(DataNotFoundException exception) {
        log.error("dataNotFoundException Handle={}", exception);
        return ApiResponse.error(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(InvalidAccessResourceException.class)
    public ResponseEntity<Error> exceptionHandle(InvalidAccessResourceException exception) {
        log.error("InvalidAccessResourceException Handle={}", exception);
        return ApiResponse.error(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exceptionHandle(Exception exception) {
        log.error("exceptionHadnle={}", exception);
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
