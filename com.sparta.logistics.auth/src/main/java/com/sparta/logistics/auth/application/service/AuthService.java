package com.sparta.logistics.auth.application.service;

import com.sparta.logistics.auth.application.outputport.UserOutputPort;
import com.sparta.logistics.auth.domain.User;
import com.sparta.logistics.auth.domain.UserForCreate;
import com.sparta.logistics.auth.domain.UserForSignIn;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;
    private final UserOutputPort memberOutputPort;

    public String signIn(UserForSignIn memberForSignIn) {

        User member = memberOutputPort.findByUsername(memberForSignIn.username()).orElseThrow(
            () -> new IllegalArgumentException("member Not Found")
        );
        member.tryToSignIn(memberForSignIn, passwordEncoder);

        return accessTokenService.createActorToken(member);
    }

    @Transactional
    public void signUp(UserForCreate memberForCreate) {

        if (memberOutputPort.findByUsername(memberForCreate.getUsername()).isPresent()) {
            throw new IllegalArgumentException("username duplicated");
        }
        memberForCreate = memberForCreate.encryptPassword(passwordEncoder);
        memberOutputPort.saveOne(memberForCreate);
    }
}
