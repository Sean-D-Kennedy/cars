package com.preowned.cars.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class CarRegNoNotFoundException extends RuntimeException{
    public CarRegNoNotFoundException(String message){
        super(message);
    }
}
