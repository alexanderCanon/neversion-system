package com.neversion.store.user.infrastructure.in.rest;

import com.neversion.store.user.application.port.in.CreateUserUseCase;
import com.neversion.store.user.domain.model.Role;
import com.neversion.store.user.domain.model.User;
import com.neversion.store.user.domain.model.enums.UserRole;
import com.neversion.store.user.infrastructure.in.rest.dto.UserRequest;
import com.neversion.store.user.infrastructure.in.rest.dto.UserResponse;
import com.neversion.store.user.infrastructure.in.rest.mapper.UserRestMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Only web (controllers) no repository, no service
@WebMvcTest(UserController.class)
public class UserControllerTest {

        // HTTP Client (Postman)
        @Autowired
        private MockMvc mockMvc;

        // Tool, Convert Java Objects to JSON String
        @Autowired
        private ObjectMapper objectMapper;

        // Spring creates Mock to inject to UserController
        // UseCase (interface) no implementation
        @MockitoBean
        private CreateUserUseCase createUserUseCase;

        // Controller uses Mapper
        @MockitoBean
        private UserRestMapper userRestMapper;

        @Test
        @DisplayName("POST /users - should return 201/200 and created user")
        void shouldCreateUser_WhenRequestIsValid() throws Exception {

                // --- ARRANGE ---
                // JSON that customer sends
                UserRequest requestDto = new UserRequest(
                                "Alexander",
                                "Canon",
                                "alexandercanon64@gmail.com",
                                "myultrasecretpass",
                                "5757-7853",
                                "ADMIN");

                // Domain object that we expect mapper generates
                User domainUser = new User(
                                null,
                                "Alexander",
                                "Canon",
                                "alexandercanon64@gmail.com",
                                "myultrasecretpass",
                                "5757-7853",
                                true,
                                new Role(null, UserRole.ADMIN, null),
                                null,
                                null);

                // Domain object already ID
                User savedUser = new User(
                                1L,
                                "Alexander",
                                "Canon",
                                "alexandercanon64@gmail.com",
                                "myultrasecretpass",
                                "5757-7853",
                                true,
                                new Role(1, UserRole.ADMIN, null),
                                null,
                                null);

                UserResponse responseDto = new UserResponse(
                                1L,
                                "Alexander",
                                "Canon",
                                "alexandercanon64@gmail.com",
                                "5757-7853",
                                new UserResponse.RoleResponse("ADMIN", null));

                // --- MOCKS BEHAVIOR ---
                // When controller calls mapper 'toDomain' returns domain user
                when(userRestMapper.toDomain(any(UserRequest.class))).thenReturn(domainUser);
                // When controller calls UseCase, returns saved user
                when(createUserUseCase.createUser(any(User.class))).thenReturn(savedUser);
                // When controller calls mapper 'toResponse' returns final DTO
                when(userRestMapper.toResponse(any(User.class))).thenReturn(responseDto);

                // ---ACT & ASSERT ---
                mockMvc.perform(post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDto))) // DTO to JSON String

                                // Verifications
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.email").value("alexandercanon64@gmail.com"))
                                .andExpect(jsonPath("$.role.name").value("ADMIN"));

        }

}
