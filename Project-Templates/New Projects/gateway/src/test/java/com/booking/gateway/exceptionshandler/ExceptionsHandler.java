package com.booking.gateway.exceptionshandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Format> handleRestClientException(RestClientException e){
        return new ResponseEntity<>(Format.builder().status(HttpStatus.SERVICE_UNAVAILABLE.name()).message(e.getMessage()).build(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Format> handleException(Exception e){
        return new ResponseEntity<>(Format.builder().status(HttpStatus.SERVICE_UNAVAILABLE.name()).message(e.getMessage()).build(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
