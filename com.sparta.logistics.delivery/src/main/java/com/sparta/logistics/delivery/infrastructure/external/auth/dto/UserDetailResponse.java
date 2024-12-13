package com.sparta.logistics.delivery.infrastructure.external.auth.dto;

public record UserDetailResponse(
        Long userId,
        String username,
        String snsAccount,
        String role) {
}