package com.example.onlinefoodstorage.service_managers;

import com.example.onlinefoodstorage.annotations.ServiceManager;
import com.example.onlinefoodstorage.dtos.categories.CategoryRequest;
import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import com.example.onlinefoodstorage.entities.Category;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.mappers.interfaces.CategoryMapper;
import com.example.onlinefoodstorage.service_managers.interfaces.CategoryServiceManager;
import com.example.onlinefoodstorage.services.interfaces.CategoryService;
import com.example.onlinefoodstorage.services.interfaces.ProductService;
import com.example.onlinefoodstorage.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@ServiceManager
@RequiredArgsConstructor
public class CategoryServiceManagerImpl implements CategoryServiceManager {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseEntity<CategoryResponse> create(CategoryRequest request) {
        User user = userService.getUserById(request.getEmployeeId().toString());
        Category category = categoryMapper.toEntity(request, user);
        categoryService.create(category);
        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @Override
    public ResponseEntity<CategoryResponse> update(CategoryRequest request) {
        categoryService.getById(request.getId());
        User user = userService.getUserById(request.getEmployeeId().toString());

        Category category = categoryMapper.toEntity(request, user);
        category.setId(request.getId());
        categoryService.update(category);
        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @Override
    public ResponseEntity<CategoryResponse> getById(Integer id) {
        Category category = categoryService.getById(id);
        int productsQuantity = productService.countByCategoryId(category.getId()).size();
        return ResponseEntity.ok(categoryMapper.toResponse(category, productsQuantity));
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }

}
