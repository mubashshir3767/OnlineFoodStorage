package com.example.onlinefoodstorage.controllers.interfaces;

import com.example.onlinefoodstorage.dtos.categories.CategoryRequest;
import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoryController {
    ResponseEntity<CategoryResponse> create(CategoryRequest request);

    ResponseEntity<CategoryResponse> getById(@PathVariable Integer id);

    ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest request);

    ResponseEntity<String> delete(@PathVariable Integer id);
}
