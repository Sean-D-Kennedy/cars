package com.preowned.cars.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

// A class to get more detail back to the client (by using the HTTP response body)
@Data @AllArgsConstructor
public class DetailedErrorResponse {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
}
