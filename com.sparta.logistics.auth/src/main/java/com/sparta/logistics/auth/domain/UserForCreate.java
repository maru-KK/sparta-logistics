package com.sparta.logistics.auth.domain;

import com.sparta.logistics.auth.domain.vo.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserForCreate {

    private String username;
    private String password;
    private String snsAccount;
    private Role role;

    public UserForCreate(String username, String password, String snsAccount, Role role) {
        this.username = username;
        this.password = password;
        this.snsAccount = snsAccount;
        this.role = role;
    }

    public UserForCreate encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
        return this;
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

    public Role getRole() {
        return role;
    }
}
