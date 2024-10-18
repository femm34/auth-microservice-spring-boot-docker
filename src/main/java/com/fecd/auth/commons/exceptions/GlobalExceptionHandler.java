package com.fecd.auth.commons.exceptions;

import com.fecd.auth.commons.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {AuthException.class})
    ResponseEntity<ErrorResponseDto> handleError(AuthException authException) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                .status(authException.getHttpStatus().value())
                .message(authException.getMessage())
                .build();

        return ResponseEntity.status(authException.getHttpStatus()).body(errorResponse);
    }
}