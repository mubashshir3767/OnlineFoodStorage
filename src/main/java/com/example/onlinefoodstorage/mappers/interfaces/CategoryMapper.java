package com.example.onlinefoodstorage.mappers.interfaces;

import com.example.onlinefoodstorage.dtos.categories.CategoryRequest;
import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import com.example.onlinefoodstorage.entities.Category;
import com.example.onlinefoodstorage.entities.User;

public interface CategoryMapper {
    Category toEntity(CategoryRequest categoryRequest, User user);

    CategoryResponse toResponse(Category category, Integer productsQuantity);

    CategoryResponse toResponse(Category category);
}
