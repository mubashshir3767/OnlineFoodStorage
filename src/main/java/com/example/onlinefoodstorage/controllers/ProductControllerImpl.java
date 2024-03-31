package com.example.onlinefoodstorage.controllers;

import com.example.onlinefoodstorage.controllers.interfaces.ProductController;
import com.example.onlinefoodstorage.dtos.products.ProductRequest;
import com.example.onlinefoodstorage.dtos.products.ProductResponse;
import com.example.onlinefoodstorage.service_managers.interfaces.ProductServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductControllerImpl implements ProductController {

    private final ProductServiceManager productServiceManager;

    @Override
    @PostMapping("save")
    public ResponseEntity<ProductResponse> create(ProductRequest request) {
        return productServiceManager.create(request);
    }

    @Override
    @GetMapping("getById/{id}")
    public ResponseEntity<ProductResponse> getById(Integer id) {
        return productServiceManager.getById(id);
    }

    @Override
    @PutMapping("update")
    public ResponseEntity<ProductResponse> update(ProductRequest request) {
        return productServiceManager.update(request);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(Integer id) {
        productServiceManager.delete(id);
        return ResponseEntity.ok("OBJECT HAS BEEN SUCCESSFULLY DELETED");
    }
}
