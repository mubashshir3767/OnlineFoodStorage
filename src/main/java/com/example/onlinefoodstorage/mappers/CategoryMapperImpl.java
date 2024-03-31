package com.example.onlinefoodstorage.mappers;

import com.example.onlinefoodstorage.annotations.Mapper;
import com.example.onlinefoodstorage.dtos.categories.CategoryRequest;
import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.entities.Category;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.enums.Status;
import com.example.onlinefoodstorage.mappers.interfaces.CategoryMapper;
import com.example.onlinefoodstorage.mappers.interfaces.UserMapper;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Mapper
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {

    private final UserMapper userMapper;

    @Override
    public Category toEntity(CategoryRequest categoryRequest, User employee) {
        return Category.builder()
                .type(categoryRequest.getType())
                .employee(employee)
                .createdTime(LocalDateTime.now())
                .status(Status.ACTIVE).build();
    }

    @Override
    public CategoryResponse toResponse(Category category,Integer productsQuantity) {
        UserResponse userResponse = userMapper.toResponse(category.getEmployee());
       return CategoryResponse.builder()
               .id(category.getId())
               .type(category.getType())
               .productsQuantity(productsQuantity)
               .status(category.getStatus().toString())
               .createdTime(category.getCreatedTime().toString())
               .employee(userResponse).build();
    }

    @Override
    public CategoryResponse toResponse(Category category) {
        UserResponse userResponse = userMapper.toResponse(category.getEmployee());
        return CategoryResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .status(category.getStatus().toString())
                .createdTime(category.getCreatedTime().toString())
                .employee(userResponse).build();
    }
}
