package com.example.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalErrorHandling {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ApiError> handleException(APIException ex, WebRequest request){
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND);
        error.setTimeStamp(new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
