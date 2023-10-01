// package com.inventory.Order.exceptionhandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<ExceptionFormat> handleInvalidIdException(InvalidIdException e) {
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ExceptionFormat> handleNoDataException(NoDataException e) {
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(HttpStatus.NOT_FOUND.name()).message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MismatchException.class)
    public ResponseEntity<ExceptionFormat> handleMismatchException(MismatchException e) {
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionFormat> handleMismatchException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ExceptionFormat> handleRestClientException(RestClientResponseException e) {
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(e.getResponseBodyAs(ExceptionFormat.class).getStatusCode()).message(e.getResponseBodyAs(ExceptionFormat.class).getMessage()).build(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionFormat> handleException(Exception e) {
        return new ResponseEntity<>(ExceptionFormat.builder().statusCode(HttpStatus.BAD_REQUEST.name()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
