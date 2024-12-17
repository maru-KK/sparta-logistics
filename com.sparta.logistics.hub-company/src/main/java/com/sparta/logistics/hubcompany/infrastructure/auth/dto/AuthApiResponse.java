package com.sparta.logistics.hubcompany.infrastructure.auth.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthApiResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Success<T> {

        private T data;

        public static <T> Success<T> of(@NonNull T data) {
            return new Success<>(data);
        }
    }
}