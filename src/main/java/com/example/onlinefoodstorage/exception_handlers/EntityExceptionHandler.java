package com.example.onlinefoodstorage.exception_handlers;

import com.example.onlinefoodstorage.dtos.ExceptionResponse;
import com.example.onlinefoodstorage.exceptions.NotFoundException;
import com.example.onlinefoodstorage.utils.ExceptionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException notFoundException) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ExceptionUtil.getResponse(notFoundException, httpStatus);
    }

}
