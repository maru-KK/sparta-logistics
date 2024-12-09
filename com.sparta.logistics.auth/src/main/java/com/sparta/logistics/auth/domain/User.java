package com.sparta.logistics.auth.domain;

import com.sparta.logistics.auth.domain.vo.Role;
import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {

    private Long userId;
    private String username;
    private String password;
    private String snsAccount;
    private Role role;

    public User(Long memberId, String username, String password, String snsAccount, Role role) {
        this.userId = memberId;
        this.username = username;
        this.password = password;
        this.snsAccount = snsAccount;
        this.role = role;
    }

    public User tryToSignIn(UserForSignIn memberForSignIn, PasswordEncoder passwordEncoder) {
        if (matchesPassword(memberForSignIn.password(), passwordEncoder) &&
            matchesUsername(memberForSignIn.username())) {
            return this;
        }
        throw new IllegalArgumentException("유효하지 않은 username 혹은 passoword");
    }

    private boolean matchesPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }

    private boolean matchesUsername(String username) {
        return Objects.equals(this.username, username);
    }

    public String getRole() {
        return role.name();
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSnsAccount() {
        return snsAccount;
    }
}
