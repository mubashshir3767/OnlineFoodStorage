package com.example.onlinefoodstorage.services.interfaces;

import com.example.onlinefoodstorage.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService extends CrudService<Product, Integer, Product, Product> {
    Optional<Product> findById(Integer id);

    List<Integer> countByCategoryId(Integer id);
}
