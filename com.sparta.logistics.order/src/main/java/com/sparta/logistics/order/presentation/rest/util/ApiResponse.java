
package com.sparta.logistics.order.presentation.rest.util;

import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse {

    private static final String HEADER_ACCESS_TOKEN = "Access-Token";

    public static ResponseEntity<Object> success(HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).build();
    }

    public static <T> ResponseEntity<Success<T>> success(T data, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(Success.of(data));
    }

    public static ResponseEntity<Object> successWithAccessToken(
        HttpStatus httpStatus, String accessToken
    ) {
        return ResponseEntity.status(httpStatus)
            .header(HEADER_ACCESS_TOKEN, accessToken)
            .build();
    }

    public static ResponseEntity<Object> successWithHeaders(
        HttpStatus httpStatus, Integer port, Map<String, String> headers
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);

        return ResponseEntity.status(httpStatus)
            .headers(httpHeaders)
            .build();
    }

    public static ResponseEntity<Error> error(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
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

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Success<T> {

        private T data;

        public static <T> Success<T> of(@NonNull T data) {
            return new Success<>(data);
        }
    }
}
