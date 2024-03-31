package com.example.onlinefoodstorage.utils;

import com.example.onlinefoodstorage.dtos.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static ResponseEntity<ExceptionResponse> getResponse(RuntimeException exception, HttpStatus status) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(exception.getMessage())
                .code(status.value())
                .httpStatus(status).build();
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
