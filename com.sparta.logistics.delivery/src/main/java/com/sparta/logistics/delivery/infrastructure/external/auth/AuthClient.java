package com.sparta.logistics.delivery.infrastructure.external.auth;

import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.AuthApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {
    @GetMapping("/auth/user/{userId}")
    ResponseEntity<AuthApiResponse.Success<UserDetailResponse>> findUser(@PathVariable("userId") Long userId);
}
