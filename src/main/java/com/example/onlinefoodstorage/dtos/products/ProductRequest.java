package com.example.onlinefoodstorage.dtos.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Integer id;

    @NotBlank
    private String name;

    @Min(value = 1)
    private Double quantity;

    private LocalDateTime validityTime;

    @Min(value = 1)
    private Integer categoryId;

    private Integer employeeId;
}
