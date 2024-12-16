package com.sparta.logistics.infra.persistence.rest.exception;


import com.sparta.logistics.infra.persistence.rest.util.ApiResponse;
import com.sparta.logistics.infra.persistence.rest.util.ApiResponse.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exceptionHandler(Exception exception) {
        log.error("runtimeException Handle= ", exception);
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
