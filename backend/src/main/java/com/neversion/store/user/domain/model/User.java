package com.neversion.store.user.domain.model;

import java.time.LocalDateTime;

public record User(
    Long id,
    String firstName,
    String lastName,
    String email,
    String password,
    String phoneNumber,
    boolean isActive,
    Role role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
