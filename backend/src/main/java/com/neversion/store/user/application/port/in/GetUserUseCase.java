package com.neversion.store.user.application.port.in;

import java.util.Optional;

import com.neversion.store.user.domain.model.User;

public interface GetUserUseCase {
    Optional<User> findById(Long id);
}
