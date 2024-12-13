package com.sparta.logistics.auth.presentation.rest.controller.user;

import com.sparta.logistics.auth.domain.User;
import com.sparta.logistics.auth.infrastructure.persistence.adapter.UserAdapter;
import com.sparta.logistics.auth.presentation.rest.dto.UserDetailResponse;
import com.sparta.logistics.auth.presentation.rest.exception.NotFoundException;
import com.sparta.logistics.auth.presentation.util.AuthApiResponse;
import com.sparta.logistics.auth.presentation.util.AuthApiResponse.Success;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/user")
@RestController
public class UserQueryController {

    private final UserAdapter userAdapter;

    @GetMapping("/{userId}")
    public ResponseEntity<Success<UserDetailResponse>> findUser(@PathVariable("userId") Long userId) {
        User user = userAdapter.findById(userId).orElseThrow(() ->
            new NotFoundException("존재하지 않는 회원입니다."));

        UserDetailResponse response = UserDetailResponse.from(user);
        return AuthApiResponse.success(response, HttpStatus.OK);
    }
}
