package com.neversion.store.user.domain.model;

import com.neversion.store.user.domain.model.enums.UserRole;

public record Role(
    Integer id,
    UserRole name,
    String description
) {}
