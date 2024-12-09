package com.sparta.logistics.auth.presentation.rest.controller.auth;

import com.sparta.logistics.auth.application.service.AuthService;
import com.sparta.logistics.auth.presentation.util.AuthApiResponse;
import com.sparta.logistics.auth.presentation.rest.dto.SignInRequest;
import com.sparta.logistics.auth.presentation.rest.dto.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        String accessToken = authService.signIn(signInRequest.toDomain());
        return AuthApiResponse.successWithAccessToken(HttpStatus.NO_CONTENT, accessToken);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest.toDomain());
        return AuthApiResponse.success(HttpStatus.CREATED);
    }
}
