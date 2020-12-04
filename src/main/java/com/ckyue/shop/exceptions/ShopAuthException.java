package com.ckyue.shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ShopAuthException extends RuntimeException{
    public ShopAuthException(String message) {
        super(message);
    }
}
