package de.materna.trainee.date4u.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value=BAD_REQUEST)
public class Response400Exception extends RuntimeException{

    public Response400Exception() {
    }

    public Response400Exception(String message) {
        super(message);
    }

    public Response400Exception(Throwable cause) {
        super(cause);
    }
}
