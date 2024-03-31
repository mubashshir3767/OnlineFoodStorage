package com.example.onlinefoodstorage.services.interfaces;

import com.example.onlinefoodstorage.entities.Category;

import java.util.Optional;

public interface CategoryService extends CrudService<Category,Integer,Category,Category> {
    Optional<Category> findById(Integer id);
}
