package com.swisscom.DataAPIChallenge.exception;

import org.springframework.http.HttpStatus;

public class DataAPIException extends RuntimeException{

    protected final HttpStatus status;

    public DataAPIException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

}
