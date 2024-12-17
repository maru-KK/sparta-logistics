package com.sparta.logistics.hubcompany.infrastructure.auth;

import com.sparta.logistics.hubcompany.infrastructure.auth.dto.AuthApiResponse;
import com.sparta.logistics.hubcompany.infrastructure.auth.dto.UserDetailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {
    @GetMapping("/api/v1/auth/user/{userId}")
    ResponseEntity<AuthApiResponse.Success<UserDetailResponse>> findUser(@PathVariable Long userId);
}