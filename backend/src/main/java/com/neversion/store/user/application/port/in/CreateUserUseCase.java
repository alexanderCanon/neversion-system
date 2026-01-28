package com.neversion.store.user.application.port.in;

import com.neversion.store.user.domain.model.User;


public interface CreateUserUseCase {
    User createUser(User user);


}
