package com.security.authentication.service;

import com.security.authentication.config.PasswordConstraintValidator;
import com.security.authentication.exeptions.InvalidPasswordException;
import com.security.authentication.exeptions.InvalidTokenException;
import com.security.authentication.model.User;
import com.security.authentication.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private JwtService jwtService;
    @Autowired private PasswordConstraintValidator validator;

    @Override
    public User register(User user) throws InvalidPasswordException {
        validator.isValid(user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public String generate(String username, String password) {
        User user = repo.findByUsername(username).get();
        return jwtService.generateToken(username, user.getRole());
    }

    @Override
    public String validate(String token) throws InvalidTokenException {
        return jwtService.validateToken(token);
    }

}
