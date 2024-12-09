package com.sparta.logistics.auth.presentation.rest.dto;

import com.sparta.logistics.auth.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {

    private Long userId;
    private String username;
    private String snsAccount;
    private String role;

    public static UserDetailResponse from(User user) {
        return new UserDetailResponse(
            user.getUserId(),
            user.getUsername(),
            user.getSnsAccount(),
            user.getRole()
        );
    }
}
