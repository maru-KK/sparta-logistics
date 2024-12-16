package com.sparta.logistics.hubcompany.infrastructure.auth;

public interface AuthPort {
    com.sparta.logistics.hubcompany.infrastructure.auth.dto.UserDetailResponse findUser(Long userId);
}
