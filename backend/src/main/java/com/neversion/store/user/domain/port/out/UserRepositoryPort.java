package com.neversion.store.user.domain.port.out;
import java.util.Optional;

import com.neversion.store.user.domain.model.User;


public interface UserRepositoryPort {

    User save(User user);
    Optional<User> findById(Long id);
    boolean existsByEmail(String email);
}
/**
 * This interface defines the contract for persistent storage and retrieval
 * of User objects in the system. It acts as a port in the domain layer,
 * abstracting away the details of the underlying data source. 
 * Typical implementations could interact with databases or other external services.
 * 
 * Methods:
 *  - save(User user): Persists a User entity and returns the saved entity, which may include updates (e.g., generated ID).
 *  - findById(Long id): Retrieves a User entity by its unique identifier, returning an Optional that is empty if not found.
 */

