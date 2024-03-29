package com.example.onlinefoodstorage.dtos;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionHandlerResponse {

    private final String message;

    private Object data;

    private final Integer code;

    private final HttpStatus httpStatus;

    public ExceptionHandlerResponse(String message, Integer code, HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ExceptionHandlerResponse(String message, Object data, Integer code, HttpStatus httpStatus) {
        this.message = message;
        this.data = data;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
