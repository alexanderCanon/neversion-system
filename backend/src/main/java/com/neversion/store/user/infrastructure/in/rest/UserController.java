package com.neversion.store.user.infrastructure.in.rest;

import com.neversion.store.user.application.port.in.CreateUserUseCase;
import com.neversion.store.user.application.port.in.GetUserUseCase;
import com.neversion.store.user.domain.model.User;
import com.neversion.store.user.infrastructure.in.rest.dto.UserRequest;
import com.neversion.store.user.infrastructure.in.rest.dto.UserResponse;
import com.neversion.store.user.infrastructure.in.rest.mapper.UserRestMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UserRestMapper userRestMapper;

    public UserController(CreateUserUseCase createUserUseCase, GetUserUseCase getUserUseCase, UserRestMapper userRestMapper) {
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.userRestMapper = userRestMapper;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        User user = userRestMapper.toDomain(userRequest);
        User userCreated = createUserUseCase.createUser(user);
        return userRestMapper.toResponse(userCreated);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        User user = getUserUseCase.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userRestMapper.toResponse(user);
    }
}
