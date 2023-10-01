package com.booking.gateway.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedUserException extends ResponseStatusException {

    public UnauthorizedUserException(HttpStatusCode status) {
        super(status);
    }

    public UnauthorizedUserException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public UnauthorizedUserException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public UnauthorizedUserException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected UnauthorizedUserException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }

}
