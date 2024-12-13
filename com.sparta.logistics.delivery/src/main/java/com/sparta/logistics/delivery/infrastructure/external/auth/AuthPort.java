package com.sparta.logistics.delivery.infrastructure.external.auth;

import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;

public interface AuthPort {
    UserDetailResponse findUser(Long userId);
}
