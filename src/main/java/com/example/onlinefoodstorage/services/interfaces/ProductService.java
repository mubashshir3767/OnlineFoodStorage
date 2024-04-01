package com.example.onlinefoodstorage.services.interfaces;

import com.example.onlinefoodstorage.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService extends CrudService<Product, Integer, Product, Product> {
    Optional<Product> findById(Integer id);

    Integer countByCategoryId(Integer id);

    Page<Product> findByEmployeeId(Pageable of, Integer employeeID);
}
