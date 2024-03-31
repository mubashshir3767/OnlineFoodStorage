package com.example.onlinefoodstorage.service_managers.interfaces;

import com.example.onlinefoodstorage.dtos.categories.CategoryRequest;
import com.example.onlinefoodstorage.dtos.categories.CategoryResponse;
import org.springframework.http.ResponseEntity;

public interface CategoryServiceManager extends CrudServiceManager<ResponseEntity<CategoryResponse>, CategoryRequest,Integer>{
}
