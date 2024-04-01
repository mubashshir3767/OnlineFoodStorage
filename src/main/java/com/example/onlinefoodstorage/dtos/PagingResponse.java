package com.example.onlinefoodstorage.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse<T> {
    private List<T> list;
    private Long totalItemCount;
    private Integer page;
    private Boolean hasMore;
}
