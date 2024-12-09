package com.sparta.logistics.auth.infrastructure.persistence.entity;

import com.sparta.logistics.auth.domain.vo.Role;

public enum UserEntityRole {
    MASTER, HUB, COMPANY, DELIVERY;

    public Role toDomain() {
        return Role.valueOf(this.name());
    }

    public static UserEntityRole from(Role role) {
        return UserEntityRole.valueOf(role.name());
    }
}
