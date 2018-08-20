package com.exxeta.maau.demoliquibase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequestErrorException extends RuntimeException {

    public RequestErrorException(String message){
        super(message);
    }

    public RequestErrorException(String message, Throwable cause){
        super(message, cause);
    }


}
