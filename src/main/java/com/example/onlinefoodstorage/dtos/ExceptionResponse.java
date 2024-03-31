package com.example.onlinefoodstorage.dtos;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ExceptionResponse {

    private final String message;

    private Object data;

    private final Integer code;

    private final HttpStatus httpStatus;

}
