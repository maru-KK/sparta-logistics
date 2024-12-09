package com.sparta.logistics.auth.application.outputport;

import com.sparta.logistics.auth.domain.User;
import com.sparta.logistics.auth.domain.UserForCreate;
import java.util.Optional;

public interface UserOutputPort {

    Optional<User> findByUsername(String username);

    User saveOne(UserForCreate memberForCreate);
}
