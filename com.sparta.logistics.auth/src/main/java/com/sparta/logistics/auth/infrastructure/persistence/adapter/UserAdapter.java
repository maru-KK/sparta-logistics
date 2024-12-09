package com.sparta.logistics.auth.infrastructure.persistence.adapter;

import com.sparta.logistics.auth.application.outputport.UserOutputPort;
import com.sparta.logistics.auth.domain.User;
import com.sparta.logistics.auth.domain.UserForCreate;
import com.sparta.logistics.auth.infrastructure.persistence.entity.UserEntity;
import com.sparta.logistics.auth.infrastructure.persistence.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserAdapter implements UserOutputPort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
            .map(UserEntity::toDomain)
            .or(Optional::empty);
    }

    @Override
    public User saveOne(UserForCreate memberForCreate) {
        UserEntity member = UserEntity.from(memberForCreate);
        return userRepository.save(member).toDomain();
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId)
            .map(UserEntity::toDomain)
            .or(Optional::empty);
    }
}
