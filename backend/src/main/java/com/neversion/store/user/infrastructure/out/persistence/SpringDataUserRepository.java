package com.neversion.store.user.infrastructure.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
