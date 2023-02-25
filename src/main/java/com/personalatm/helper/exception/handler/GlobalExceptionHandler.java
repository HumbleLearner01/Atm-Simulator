package com.personalatm.helper.exception.handler;

import com.personalatm.helper.exception.*;
import com.personalatm.helper.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotEligibleException.class)
    public ResponseEntity<ApiResponse> userNotEligibleExceptionHandler(UserNotEligibleException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AtmException.class)
    public ResponseEntity<ApiResponse> atmExceptionHandler(AtmException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiResponse> insufficientBalanceExceptionHandler(InsufficientBalanceException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoSuchAccountException.class)
    public ResponseEntity<ApiResponse> noSuchAccountExceptionHandler(NoSuchAccountException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), false), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(objectError -> {
            String defaultMessage = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            response.put("defaultMessage", defaultMessage);
            response.put("field", field);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}