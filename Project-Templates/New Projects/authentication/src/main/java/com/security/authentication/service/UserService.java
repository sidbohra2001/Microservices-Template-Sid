package com.security.authentication.service;

import com.security.authentication.exceptions.UnauthenticatedUserException;
import com.security.authentication.model.User;
import com.security.authentication.repo.UserRepo;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepo repo;
    @Autowired
    private JwtService jwtService;

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String login(User user) throws UnauthenticatedUserException{
        user = repo.findByUsername(user.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return jwtService.generateToken(user.getUsername(), user.getRoles());
    }

    public String validate(String token) throws ExpiredJwtException {
        return jwtService.validateToken(token);
    }
}
