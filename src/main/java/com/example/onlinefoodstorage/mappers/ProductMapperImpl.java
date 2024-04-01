package com.example.onlinefoodstorage.mappers;

import com.example.onlinefoodstorage.annotations.Mapper;
import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import com.example.onlinefoodstorage.dtos.products.ProductRequest;
import com.example.onlinefoodstorage.dtos.products.ProductResponse;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.entities.Category;
import com.example.onlinefoodstorage.entities.Product;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.enums.Status;
import com.example.onlinefoodstorage.mappers.interfaces.CategoryMapper;
import com.example.onlinefoodstorage.mappers.interfaces.ProductMapper;
import com.example.onlinefoodstorage.mappers.interfaces.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {

    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public Product toEntity(ProductRequest productRequest, User user, Category category) {
        return Product.builder()
                .name(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .category(category)
                .status(productRequest.getQuantity() > 0 ? Status.ACTIVE : Status.NON_ACTIVE)
                .employee(user)
                .validityTime(productRequest.getValidityTime())
                .createdTime(LocalDateTime.now()).build();
    }

    @Override
    public ProductResponse toResponse(Product product) {
        CategoryResponse categoryResponse = categoryMapper.toResponse(product.getCategory());
        UserResponse userResponse = userMapper.toResponse(product.getEmployee());
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .employee(userResponse)
                .category(categoryResponse)
                .quantity(product.getQuantity())
                .status(product.getStatus().toString())
                .validityTime(product.getValidityTime().toString())
                .createdTime(product.getCreatedTime().toString()).build();
    }

    @Override
    public PagingResponse<ProductResponse> toResponse(Page<Product> productPage) {
        List<ProductResponse> list = productPage.stream().map(this::toResponse).toList();
        return new PagingResponse<>(list, productPage.getTotalElements(), productPage.getTotalPages(), productPage.hasContent());
    }
}
