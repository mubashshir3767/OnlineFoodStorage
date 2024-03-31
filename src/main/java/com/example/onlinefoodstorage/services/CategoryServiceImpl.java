package com.example.onlinefoodstorage.services;

import com.example.onlinefoodstorage.entities.Category;
import com.example.onlinefoodstorage.repos.CategoryRepo;
import com.example.onlinefoodstorage.services.interfaces.CategoryService;
import com.example.onlinefoodstorage.utils.OptionalEntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Category create(Category entity) {
        return categoryRepo.save(entity);
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepo.findById(id);
    }


    @Override
    public Category getById(Integer id) {
        Optional<Category> optionalCategory = this.findById(id);
        return OptionalEntityUtil.getIfPresent(optionalCategory);
    }

    @Override
    public Category update(Category entity) {
        return categoryRepo.save(entity);
    }

    @Override
    public void delete(Integer integer) {
        Category category = this.getById(integer);
        categoryRepo.delete(category);
    }
}
