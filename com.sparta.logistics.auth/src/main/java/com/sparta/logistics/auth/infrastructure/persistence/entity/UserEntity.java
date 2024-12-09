package com.sparta.logistics.auth.infrastructure.persistence.entity;

import com.sparta.logistics.auth.domain.User;
import com.sparta.logistics.auth.domain.UserForCreate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_user")
@Entity(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String snsAccount;

    @Enumerated(EnumType.STRING)
    private UserEntityRole role;

    public User toDomain() {
        return new User(id, username, password, snsAccount, role.toDomain());
    }

    public static UserEntity from(UserForCreate memberForCreate) {
        return new UserEntity(
            null,
            memberForCreate.getUsername(),
            memberForCreate.getPassword(),
            memberForCreate.getSnsAccount(),
            UserEntityRole.from(memberForCreate.getRole())
        );
    }
}
