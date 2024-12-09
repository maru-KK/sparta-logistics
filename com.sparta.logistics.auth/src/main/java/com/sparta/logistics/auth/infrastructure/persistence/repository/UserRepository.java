package com.sparta.logistics.auth.infrastructure.persistence.repository;

import com.sparta.logistics.auth.infrastructure.persistence.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
