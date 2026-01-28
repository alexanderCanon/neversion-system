package com.neversion.store.user.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.neversion.store.user.domain.model.User;
import com.neversion.store.user.domain.port.out.UserRepositoryPort;

@ExtendWith(MockitoExtension.class) // Enable @Mock annotations
class CreateUserServiceTest {
    // Mock: out port simulation (repository)

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks // Instance real class that wanted to test, later inject defined mock up inside
                 // itself
    private CreateUserService createUserService;

    // ESCENARIO 1: Successful creation
    @Test
    @DisplayName("Should create user succesfuly when email is unique")
    void shouldCreateUser_WhenEmailIsUnique() {
        // ARRANGE
        User userToSave = buildDummyUser(null);
        User savedUser = buildDummyUser(1L);

        // Teaches mock what to do, "existsbyEmail false (say no)"
        when(userRepositoryPort.existsByEmail(userToSave.email())).thenReturn(false);

        when(userRepositoryPort.save(any(User.class))).thenReturn(savedUser);

        // ACT
        User result = createUserService.createUser(userToSave);

        // ASSERT (verify results)
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(userToSave.email(), result.email());

        // Verify behaviour, let's make sure that service called repository once
        verify(userRepositoryPort).save(userToSave);
    }

    // ESCENARIO 2: Fail by Duplicated Email
    @Test
    @DisplayName("Should throw exception when email already exists")
    void shouldThrowException_WhenEmailExists() {
        // ARRANGE
        User userToSave = buildDummyUser(null);

        // simulate email already exists
        when(userRepositoryPort.existsByEmail(userToSave.email())).thenReturn(true);

        // ACT & ASSERT
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    createUserService.createUser(userToSave);
                });
        assertEquals("Email already exists", exception.getMessage());

        // verify never tries to save
        verify(userRepositoryPort, never()).save(any());
    }

    // Helper method to create users fast (since it's a Record)
    private User buildDummyUser(Long id) {
        return new User(
                id,
                "Alexander",
                "Canon",
                "alexandercanon@gmail.com",
                "myultrasecretpass",
                "5757-7853",
                true,
                null, // new Role()
                LocalDateTime.now(),
                LocalDateTime.now());
    }
}
