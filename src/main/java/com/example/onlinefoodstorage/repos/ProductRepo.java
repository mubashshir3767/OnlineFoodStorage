package com.example.onlinefoodstorage.repos;

import com.example.onlinefoodstorage.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Integer> countByCategory_Id(Integer id);
}
