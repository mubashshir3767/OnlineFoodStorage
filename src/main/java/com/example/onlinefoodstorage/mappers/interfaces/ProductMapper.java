package com.example.onlinefoodstorage.mappers.interfaces;

import com.example.onlinefoodstorage.annotations.Mapper;
import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.products.ProductRequest;
import com.example.onlinefoodstorage.dtos.products.ProductResponse;
import com.example.onlinefoodstorage.entities.Category;
import com.example.onlinefoodstorage.entities.Product;
import com.example.onlinefoodstorage.entities.User;
import org.springframework.data.domain.Page;

public interface ProductMapper {
    Product toEntity(ProductRequest productRequest, User user, Category category);
    ProductResponse toResponse(Product product);

    PagingResponse<ProductResponse> toResponse(Page<Product> productPage);
}
