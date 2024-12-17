package com.sparta.logistics.hubcompany.infrastructure.auth;

import com.sparta.logistics.hubcompany.infrastructure.auth.dto.UserDetailResponse;
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
