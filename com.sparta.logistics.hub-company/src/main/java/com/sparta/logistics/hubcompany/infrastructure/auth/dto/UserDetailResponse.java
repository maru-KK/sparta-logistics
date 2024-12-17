package com.sparta.logistics.hubcompany.infrastructure.auth.dto;

public record UserDetailResponse(
        Long userId,
        String username,
        String snsAccount,
        String role) {
}