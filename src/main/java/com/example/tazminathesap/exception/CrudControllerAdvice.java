package com.example.tazminathesap.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CrudControllerAdvice {
    
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundException exception){
        ApiExceptionResponse response = new ApiExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @ExceptionHandler(AlreadyExistException.class)
    ResponseEntity<ApiExceptionResponse> handleAlreadyExistException(AlreadyExistException exception){
        ApiExceptionResponse response = new ApiExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(EntityNotCreatedException.class)
    ResponseEntity<ApiExceptionResponse> handleEntityNotCreatedException(EntityNotCreatedException exception){
        ApiExceptionResponse response = new ApiExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(TokenRefreshException.class)
    ResponseEntity<ApiExceptionResponse> handleTokenRefreshException(TokenRefreshException exception){
        ApiExceptionResponse response = new ApiExceptionResponse(exception.getMessage(), HttpStatus.FORBIDDEN, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
