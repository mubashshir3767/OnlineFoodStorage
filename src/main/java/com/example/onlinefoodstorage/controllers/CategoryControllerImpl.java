package com.example.onlinefoodstorage.controllers;

import com.example.onlinefoodstorage.controllers.interfaces.CategoryController;
import com.example.onlinefoodstorage.dtos.categories.CategoryRequest;
import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import com.example.onlinefoodstorage.service_managers.interfaces.CategoryServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories/")
public class CategoryControllerImpl implements CategoryController {

    private final CategoryServiceManager categoryServiceManager;

    @Override
    @PostMapping("save")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        return categoryServiceManager.create(request);
    }

    @Override
    @GetMapping("getById/{id}")
    public ResponseEntity<CategoryResponse> getById(Integer id) {
        return categoryServiceManager.getById(id);
    }

    @Override
    @PutMapping("update")
    public ResponseEntity<CategoryResponse> update(CategoryRequest request) {
        return categoryServiceManager.update(request);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(Integer id) {
         categoryServiceManager.delete(id);
         return ResponseEntity.ok("OBJECT HAS BEEN SUCCESSFULLY DELETED");
    }
}
