package com.neversion.store.user.application.service;

import org.springframework.stereotype.Service;

import com.neversion.store.user.application.port.in.GetUserUseCase;
import java.util.Optional;
import com.neversion.store.user.domain.model.User;
import com.neversion.store.user.domain.port.out.UserRepositoryPort;


@Service
public class GetUserService implements GetUserUseCase{

    private final UserRepositoryPort userRepositoryPort;

    public GetUserService(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<User> findById(Long id){
        return userRepositoryPort.findById(id);
    }
}
