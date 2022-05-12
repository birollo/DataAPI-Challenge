package com.swisscom.DataAPIChallenge;

import org.springframework.http.HttpStatus;

public class DataAPIException extends RuntimeException{

    protected final HttpStatus status;

    public DataAPIException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){return status;}
}
