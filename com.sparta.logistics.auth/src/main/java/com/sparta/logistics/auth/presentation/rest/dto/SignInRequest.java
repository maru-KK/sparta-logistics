package com.sparta.logistics.auth.presentation.rest.dto;

import com.sparta.logistics.auth.domain.UserForSignIn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    private String username;
    private String password;

    public UserForSignIn toDomain() {
        return new UserForSignIn(username, password);
    }
}
