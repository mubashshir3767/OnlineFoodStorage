package com.example.onlinefoodstorage.services;

import com.example.onlinefoodstorage.entities.Category;
import com.example.onlinefoodstorage.repos.CategoryRepo;
import com.example.onlinefoodstorage.services.interfaces.CategoryService;
import com.example.onlinefoodstorage.utils.OptionalEntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Category> findByEmployeeId(Pageable of, Integer employeeId) {
        return categoryRepo.findByEmployeeId(employeeId, of);
    }

    @Override
    @Cacheable(value = "categories", key = "#id")
    public Category getById(Integer id) {
        Optional<Category> optionalCategory = this.findById(id);
        return OptionalEntityUtil.getIfPresent(optionalCategory);
    }

    @Override
    @Caching(put = @CachePut(value = "categories", key = "#entity.id"),
            evict = @CacheEvict(value = "categories", key = "'list'"))
    public Category update(Category entity) {
        return categoryRepo.save(entity);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "categories", key = "'list'"),
            @CacheEvict(value = "categories", key = "#id")
    })
    public void delete(Integer id) {
        Category category = this.getById(id);
        categoryRepo.delete(category);
    }
}
