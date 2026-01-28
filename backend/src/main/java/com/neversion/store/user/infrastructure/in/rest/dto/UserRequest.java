package com.neversion.store.user.infrastructure.in.rest.dto;

public record UserRequest(
    String firstName,
    String lastName,
    String email,
    String password,
    String phoneNumber,
    String role
) {
}
