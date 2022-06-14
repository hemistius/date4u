package de.materna.trainee.date4u.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value=NOT_FOUND)
public class Response404Exception extends RuntimeException{
}
