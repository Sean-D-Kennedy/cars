package com.preowned.cars.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class CarRegNoAlreadyExistsException extends RuntimeException{
    public CarRegNoAlreadyExistsException(String message){
        super(message);
    }
}
