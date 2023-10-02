package com.security.authentication.controller;

import com.security.authentication.exceptions.UnauthenticatedUserException;
import com.security.authentication.model.User;
import com.security.authentication.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return new ResponseEntity<>(service.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) throws UnauthenticatedUserException {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(!authentication.isAuthenticated()) throw new UnauthenticatedUserException("User "+user.getUsername()+"not found");
        return new ResponseEntity<>(service.login(user), HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String token) throws ExpiredJwtException {
        return new ResponseEntity<>(service.validate(token), HttpStatus.OK);
    }
}
