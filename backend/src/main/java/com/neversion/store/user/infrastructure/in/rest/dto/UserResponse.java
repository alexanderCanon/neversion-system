package com.neversion.store.user.infrastructure.in.rest.dto;

public record UserResponse(

    Long id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    RoleResponse role

) {
    public record RoleResponse(String name, String description) {}
}
