package com.swisscom.DataAPIChallenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataAPIExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerException(HttpStatus status, String message){
        return ResponseEntity.status(status).body(message);
    }
}
