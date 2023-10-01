package com.security.authentication.service;

import com.security.authentication.exeptions.InvalidPasswordException;
import com.security.authentication.exeptions.InvalidTokenException;
import com.security.authentication.model.User;

public interface UserService {
    User register(User user) throws InvalidPasswordException;
    String generate(String username, String password);
    String validate(String token) throws InvalidTokenException;
}
