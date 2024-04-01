package com.example.onlinefoodstorage.controllers.interfaces;

import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.categories.CategoryRequest;
import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CategoryController {
    ResponseEntity<CategoryResponse> create(CategoryRequest request);

    ResponseEntity<CategoryResponse> getById(@PathVariable String id);

    ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest request);

    ResponseEntity<String> delete(@PathVariable Integer id);

    ResponseEntity<PagingResponse<CategoryResponse>> findByEmployeeId(@RequestParam(required = false,defaultValue = "0") int page,
                                                                      @RequestParam(required = false,defaultValue = "10") int size,
                                                                      @RequestParam(required = false) Integer employeeId);
}
