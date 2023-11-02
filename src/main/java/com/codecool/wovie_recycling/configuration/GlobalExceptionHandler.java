package com.codecool.wovie_recycling.configuration;

import com.codecool.wovie_recycling.exception.ContainerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ContainerNotFoundException.class)
    public ResponseEntity<Object> handleContainerNotFoundException(ContainerNotFoundException ex) {
        return new ResponseEntity<>("Container not found", HttpStatus.NOT_FOUND);
    }
}
