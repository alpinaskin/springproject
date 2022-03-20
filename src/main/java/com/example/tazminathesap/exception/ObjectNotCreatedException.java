package com.example.tazminathesap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class ObjectNotCreatedException extends RuntimeException {

    public ObjectNotCreatedException(String message){
        super(message);
    }

    public ObjectNotCreatedException(){

    }
}
