package com.ticketwar.ticketwar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandler(CustomException customException) {
        return ResponseEntity.status(customException.getExceptionStatus().getCode())
                .body(customException.getExceptionStatus());
    }
}
