package com.example.onlinefoodstorage.controllers.interfaces;

import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.products.ProductRequest;
import com.example.onlinefoodstorage.dtos.products.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductController {
    ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request);

    ResponseEntity<ProductResponse> getById(@PathVariable Integer id);

    ResponseEntity<ProductResponse> update(@RequestBody @Valid ProductRequest request);

    ResponseEntity<String> delete(@PathVariable Integer id);

    ResponseEntity<PagingResponse<ProductResponse>> findByEmployeeId(@RequestParam(required = false, defaultValue = "0") int page,
                                                                     @RequestParam(required = false, defaultValue = "10") int size,
                                                                     @RequestParam(required = false) Integer employeeID);
}
