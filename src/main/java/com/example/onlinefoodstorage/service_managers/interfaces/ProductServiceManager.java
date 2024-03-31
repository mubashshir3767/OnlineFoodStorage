package com.example.onlinefoodstorage.service_managers.interfaces;

import com.example.onlinefoodstorage.dtos.products.ProductRequest;
import com.example.onlinefoodstorage.dtos.products.ProductResponse;
import org.springframework.http.ResponseEntity;

public interface ProductServiceManager extends CrudServiceManager<ResponseEntity<ProductResponse>, ProductRequest, Integer> {
}
