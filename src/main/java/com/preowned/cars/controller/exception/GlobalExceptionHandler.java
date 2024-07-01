package com.preowned.cars.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarRegNoAlreadyExistsException.class)
    public ResponseEntity<DetailedErrorResponse> handleRegNoAlreadyExists(CarRegNoAlreadyExistsException exception,
                                                                          WebRequest webRequest){
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarRegNoNotFoundException.class)
    public ResponseEntity<DetailedErrorResponse> handleRegNoNotFound(CarRegNoNotFoundException exception,
                                                                     WebRequest webRequest){
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarRegNumbersMismatchException.class)
    public ResponseEntity<DetailedErrorResponse> handleRegNumbersMismatchOnPut(CarRegNumbersMismatchException exception,
                                                                               WebRequest webRequest){
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
