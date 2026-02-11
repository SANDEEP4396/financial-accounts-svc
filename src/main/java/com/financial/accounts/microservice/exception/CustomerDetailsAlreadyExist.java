package com.financial.accounts.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerDetailsAlreadyExist extends RuntimeException{

    public CustomerDetailsAlreadyExist(String message) {
        super(message);
    }
}
