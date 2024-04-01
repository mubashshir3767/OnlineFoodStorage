package com.example.onlinefoodstorage.repos;

import com.example.onlinefoodstorage.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Product> findByCategoryId(Integer id);

    Page<Product> findByEmployeeId(Pageable of, Integer employeeID);
}
