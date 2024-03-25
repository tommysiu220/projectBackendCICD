package com.fsse2401.projectBackend.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidProductQuantityException extends RuntimeException{
    public InvalidProductQuantityException(Integer pid){
        super(String.format("Invalid quantity: -pid: %d",pid));
    }
}
