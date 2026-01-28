package com.neversion.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.neversion.store.user.application.port.in.CreateUserUseCase;
import com.neversion.store.user.application.port.in.GetUserUseCase;
import com.neversion.store.user.application.service.CreateUserService;
import com.neversion.store.user.application.service.GetUserService;
import com.neversion.store.user.domain.port.out.UserRepositoryPort;

@Configuration
public class UserConfig {

    @Bean
    CreateUserUseCase createUser(UserRepositoryPort repo) {
        return new CreateUserService(repo);
    }

    @Bean
    GetUserUseCase getUser(UserRepositoryPort repo) {
        return new GetUserService(repo);
    }
}
