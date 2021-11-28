package com.andryjkost.superheroes.controllers;

import com.andryjkost.superheroes.exceptions.MarvelException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(basePackageClasses = CharacterController.class)
public class MarvelControllerAdvice {
    @ExceptionHandler(MarvelException.class)
    @ResponseBody
    ResponseEntity<?> handlerMarvelException(MarvelException exception) {
        return new ResponseEntity<>(exception.getErrorMessage(), exception.getHttpStatus());

    }
}
