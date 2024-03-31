package com.example.onlinefoodstorage.dtos.categories;

import com.example.onlinefoodstorage.dtos.users.UserResponse;
import lombok.*;

@Getter
@Setter
@Builder
public class CategoryResponse {

    private Integer id;

    private String type;

    private String status;

    private String createdTime;

    private Integer productsQuantity;

    private UserResponse employee;
}
