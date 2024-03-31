package com.example.onlinefoodstorage.services;

import com.example.onlinefoodstorage.entities.Product;
import com.example.onlinefoodstorage.enums.Status;
import com.example.onlinefoodstorage.repos.ProductRepo;
import com.example.onlinefoodstorage.services.interfaces.ProductService;
import com.example.onlinefoodstorage.utils.OptionalEntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public Product create(Product entity) {
        entity.setStatus(Status.ACTIVE);
        return productRepo.save(entity);
    }

    @Override
    public Product getById(Integer id) {
        Optional<Product> optionalProduct = this.findById(id);
        return OptionalEntityUtil.getIfPresent(optionalProduct);
    }

    @Override
    public Product update(Product entity) {
        return productRepo.save(entity);
    }

    @Override
    public void delete(Integer id) {
        Product product = this.getById(id);
        productRepo.delete(product);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepo.findById(id);
    }

    @Override
    public List<Integer> countByCategoryId(Integer id) {
       return productRepo.countByCategory_Id(id);
    }
}
