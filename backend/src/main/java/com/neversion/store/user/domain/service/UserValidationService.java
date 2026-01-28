package com.neversion.store.user.domain.service;

import com.neversion.store.user.domain.model.User;

public class UserValidationService {
    public static boolean validationUser (User user){
        return user.firstName() != null && user.lastName() != null;
    }
}
