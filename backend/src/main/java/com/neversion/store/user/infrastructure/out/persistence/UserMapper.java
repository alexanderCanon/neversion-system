package com.neversion.store.user.infrastructure.out.persistence;

import com.neversion.store.user.domain.model.Role;
import com.neversion.store.user.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Role toRole(RoleEntity roleEntity) {
        return new Role(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getDescription());
    }

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getPhoneNumber(),
                userEntity.isActive(),
                toRole(userEntity.getRole()),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt());
    }

    public UserEntity toEntity(User user, RoleEntity roleEntity) {
        return new UserEntity(
                user.id(),
                user.firstName(),
                user.lastName(),
                user.email(),
                user.password(),
                user.phoneNumber(),
                user.isActive(),
                roleEntity,
                user.createdAt(),
                user.updatedAt());
    }
}
