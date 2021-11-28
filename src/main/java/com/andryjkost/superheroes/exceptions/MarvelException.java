package com.andryjkost.superheroes.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MarvelException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorMessage;

    public MarvelException(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
