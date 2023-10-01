package com.booking.gateway.exceptionhandler;

import com.booking.gateway.exceptions.UnauthorizedUserException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GatewayExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UnauthorizedUserException.class)
    public Mono<ServerResponse> handleIllegalState(ServerWebExchange exchange, UnauthorizedUserException exc) {
        exchange.getAttributes().putIfAbsent(ErrorAttributes.ERROR_ATTRIBUTE, exc);
        return ServerResponse.from(ErrorResponse.builder(exc, HttpStatus.FORBIDDEN,exc.getMessage()).build());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Mono<ServerResponse> handleExpiredTokenState(ServerWebExchange exchange, ExpiredJwtException exc) {
        exchange.getAttributes().putIfAbsent(ErrorAttributes.ERROR_ATTRIBUTE, exc);
        return ServerResponse.from(ErrorResponse.builder(exc, HttpStatus.FORBIDDEN,exc.getMessage()).build());
    }
}
