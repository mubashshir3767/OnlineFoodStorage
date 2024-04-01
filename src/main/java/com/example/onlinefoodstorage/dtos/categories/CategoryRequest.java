package com.example.onlinefoodstorage.dtos.categories;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private Integer id;

    private String type;
}
