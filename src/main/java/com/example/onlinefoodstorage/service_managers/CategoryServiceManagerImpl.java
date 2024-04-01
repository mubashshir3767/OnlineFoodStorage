package com.example.onlinefoodstorage.service_managers;

import com.example.onlinefoodstorage.annotations.ServiceManager;
import com.example.onlinefoodstorage.dtos.PagingResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        User employee = userService.getCurrentUser();
        Category category = categoryMapper.toEntity(request, employee);
        categoryService.create(category);
        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @Override
    public ResponseEntity<CategoryResponse> update(CategoryRequest request) {
        categoryService.getById(request.getId());
        User employee = userService.getCurrentUser();

        Category category = categoryMapper.toEntity(request, employee);
        category.setId(request.getId());
        categoryService.update(category);
        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }

    @Override
    public ResponseEntity<CategoryResponse> getCategoryById(String id) {
        Category category = categoryService.getById(Integer.parseInt(id));
        int productsQuantity = productService.countByCategoryId(category.getId());
        return ResponseEntity.ok(categoryMapper.toResponse(category, productsQuantity));
    }

    @Override
    public ResponseEntity<PagingResponse<CategoryResponse>> findByEmployeeId(int page, int size, Integer employeeId) {
       Page<Category> categoryPage = categoryService.findByEmployeeId(PageRequest.of(page, size), employeeId);
       PagingResponse<CategoryResponse> response = categoryMapper.toResponse(categoryPage);
       return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CategoryResponse> getById(Integer id) {
        return null;
    }
}
