package com.sparta.logistics.delivery.infrastructure.external.auth;

import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthAdapter implements AuthPort{
    private final AuthClient authClient;

    @Override
    public UserDetailResponse findUser(Long userId) {
        return authClient.findUser(userId).getBody().getData();
    }
}
