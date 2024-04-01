package com.example.onlinefoodstorage.services.interfaces;

import com.example.onlinefoodstorage.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService extends CrudService<Category,Integer,Category,Category> {
    Optional<Category> findById(Integer id);

    Page<Category> findByEmployeeId(Pageable of, Integer employeeId);
}
