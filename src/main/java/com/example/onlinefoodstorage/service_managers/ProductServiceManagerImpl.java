package com.example.onlinefoodstorage.service_managers;

import com.example.onlinefoodstorage.annotations.ServiceManager;
import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.products.ProductRequest;
import com.example.onlinefoodstorage.dtos.products.ProductResponse;
import com.example.onlinefoodstorage.entities.Category;
import com.example.onlinefoodstorage.entities.Product;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.mappers.interfaces.ProductMapper;
import com.example.onlinefoodstorage.service_managers.interfaces.ProductServiceManager;
import com.example.onlinefoodstorage.services.interfaces.CategoryService;
import com.example.onlinefoodstorage.services.interfaces.ProductService;
import com.example.onlinefoodstorage.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

@ServiceManager
@RequiredArgsConstructor
public class ProductServiceManagerImpl implements ProductServiceManager {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<ProductResponse> create(ProductRequest request) {
        Category category = categoryService.getById(request.getCategoryId());
        User employee = userService.getCurrentUser();
        Product entity = productMapper.toEntity(request, employee, category);
        productService.create(entity);
        return ResponseEntity.ok(productMapper.toResponse(entity));
    }

    @Override
    public ResponseEntity<ProductResponse> update(ProductRequest request) {
        productService.getById(request.getId());
        Category category = categoryService.getById(request.getCategoryId());
        User employee = userService.getCurrentUser();

        Product entity = productMapper.toEntity(request, employee, category);
        entity.setId(request.getId());
        productService.create(entity);
        return ResponseEntity.ok(productMapper.toResponse(entity));
    }

    @Override
    public ResponseEntity<ProductResponse> getById(Integer integer) {
        Product product = productService.getById(integer);
        return ResponseEntity.ok(productMapper.toResponse(product));
    }

    @Override
    public void delete(Integer integer) {
        productService.delete(integer);
    }

    @Override
    public ResponseEntity<PagingResponse<ProductResponse>> findByEmployeeId(int page, int size, Integer employeeID) {
        Page<Product> productPage = productService.findByEmployeeId(PageRequest.of(page,size),employeeID);
        PagingResponse<ProductResponse> response = productMapper.toResponse(productPage);
        return ResponseEntity.ok(response);
    }
}
