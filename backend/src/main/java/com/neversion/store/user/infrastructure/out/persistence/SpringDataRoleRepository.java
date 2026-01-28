package com.neversion.store.user.infrastructure.out.persistence;

import com.neversion.store.user.domain.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//should @Repository annotation?
public interface SpringDataRoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(UserRole name);

    /*
     * The `findByName` method is NOT generated automatically by JPA. JPARepository
     * provides standard methods such as:
     * - save
     * - findById
     * - findAll
     * - deleteById
     * - existsById
     * - count
     */
}
