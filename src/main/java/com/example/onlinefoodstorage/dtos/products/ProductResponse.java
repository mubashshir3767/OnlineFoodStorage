package com.example.onlinefoodstorage.dtos.products;

import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import lombok.*;

@Getter
@Setter
@Builder
public class ProductResponse {

    private Integer id;

    private String name;

    private Double quantity;

    private String status;

    private String createdTime;

    private String validityTime;

    private UserResponse employee;

    private CategoryResponse category;
}
