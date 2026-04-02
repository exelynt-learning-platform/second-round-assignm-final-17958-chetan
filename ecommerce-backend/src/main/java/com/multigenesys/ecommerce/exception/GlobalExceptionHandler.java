package com.multigenesys.ecommerce.exception;

import com.multigenesys.ecommerce.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ErrorResponse internalServerError(Exception e){
        return ErrorResponse.builder()
                .msg("Internal Server Error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ErrorResponse productNotFoundException(ProductNotFoundException exception){
        return ErrorResponse.builder()
                .msg("Product Not Found")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({UserWithEmailNotExistException.class})
    public ErrorResponse userWithEmailNotExistException(UserWithEmailNotExistException ex){
        return ErrorResponse.builder()
                .msg("User with email not exist")
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
