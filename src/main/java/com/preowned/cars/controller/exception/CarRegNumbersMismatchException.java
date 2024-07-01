package com.preowned.cars.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class CarRegNumbersMismatchException extends RuntimeException{
    public CarRegNumbersMismatchException(String message){
        super(message);
    }
}
