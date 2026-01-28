package com.neversion.store.user.application.service;

import org.springframework.stereotype.Service;

import com.neversion.store.user.application.port.in.CreateUserUseCase;
import com.neversion.store.user.domain.model.User;
import com.neversion.store.user.domain.port.out.UserRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User createUser(User user) {
        if (userRepositoryPort.existsByEmail(user.email()))
            throw new IllegalArgumentException("Email already exists");
        return userRepositoryPort.save(user);
    }
}
