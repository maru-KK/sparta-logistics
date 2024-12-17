package com.sparta.logistics.hubcompany.infrastructure.auth;

import com.sparta.logistics.hubcompany.infrastructure.auth.dto.UserDetailResponse;

public interface AuthPort {
    UserDetailResponse findUser(Long userId);
}
