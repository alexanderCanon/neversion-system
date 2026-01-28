package com.neversion.store.user.infrastructure.out.persistence;

import com.neversion.store.user.domain.model.User;
import com.neversion.store.user.domain.port.out.UserRepositoryPort;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository springDataUserRepository;
    private final SpringDataRoleRepository springDataRoleRepository;
    private final UserMapper userMapper;

    public JpaUserRepositoryAdapter(SpringDataUserRepository springDataUserRepository,
            SpringDataRoleRepository springDataRoleRepository,
            UserMapper userMapper) {
        this.springDataUserRepository = springDataUserRepository;
        this.springDataRoleRepository = springDataRoleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        RoleEntity roleEntity = springDataRoleRepository.findByName(user.role().name())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        UserEntity userEntity = userMapper.toEntity(user, roleEntity);
        UserEntity savedUser = springDataUserRepository.save(userEntity);
        return userMapper.toDomain(savedUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return springDataUserRepository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springDataUserRepository.existsByEmail(email);
    }
}
