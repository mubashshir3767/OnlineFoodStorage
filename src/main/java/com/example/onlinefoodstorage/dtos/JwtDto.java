package com.example.onlinefoodstorage.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {
    private int iat;
    private int exp;
}
