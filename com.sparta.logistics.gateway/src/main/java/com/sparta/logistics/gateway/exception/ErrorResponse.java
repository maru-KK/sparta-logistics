package com.sparta.logistics.gateway.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {

    private static final String HEADER_SERVER_PORT = "Server-Port";

    public static ResponseEntity<Error> error(
        HttpStatus httpStatus, String message, String port
    ) {
        return ResponseEntity.status(httpStatus)
            .header(HEADER_SERVER_PORT, port)
            .body(Error.of(message));
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Error {

        private String message;

        public static Error of(@NonNull String errorMessage) {
            return new Error(errorMessage);
        }
    }
}
