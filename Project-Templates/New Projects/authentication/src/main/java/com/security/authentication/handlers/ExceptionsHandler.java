package com.security.authentication.handlers;

import com.security.authentication.exceptions.UnauthenticatedUserException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Format> handleExpiredJwtExceptions(ExpiredJwtException e){
        return new ResponseEntity<>(Format.builder()
                .statusCode(HttpStatus.BAD_REQUEST.name())
                .message(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthenticatedUserException.class)
    public ResponseEntity<Format> handleUnauthenticatedUserExceptions(UnauthenticatedUserException e){
        return new ResponseEntity<>(Format.builder()
                .statusCode(HttpStatus.BAD_REQUEST.name())
                .message(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Format> handleExceptions(Exception e){
        return new ResponseEntity<>(Format.builder()
                .statusCode(HttpStatus.BAD_REQUEST.name())
                .message(e.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
