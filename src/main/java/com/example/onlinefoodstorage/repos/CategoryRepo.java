package com.example.onlinefoodstorage.repos;

import com.example.onlinefoodstorage.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findById(Integer id);

    Page<Category> findByEmployeeId(Integer employeeId, Pageable pageable);
}
