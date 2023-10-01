package com.security.authentication.exceptionhandler;

import com.security.authentication.exeptions.InvalidPasswordException;
import com.security.authentication.exeptions.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Format handleInvalidPasswordException(InvalidPasswordException e){
        return Format.builder().message(e.getMessage()).status(HttpStatus.NOT_ACCEPTABLE.name()).build();
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Format handleInvalidTokenException(InvalidTokenException e){
        return Format.builder().message(e.getMessage()).status(HttpStatus.UNAUTHORIZED.name()).build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Format handleRuntimeException(RuntimeException e){
        return Format.builder().message(e.getMessage()).status(HttpStatus.UNAUTHORIZED.name()).build();
    }
}
