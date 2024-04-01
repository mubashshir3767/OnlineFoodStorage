package com.example.onlinefoodstorage.mappers.interfaces;


import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.entities.User;
import org.springframework.data.domain.Page;

public interface UserMapper {
    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);
    PagingResponse<UserResponse> toResponse(Page<User> user);
}
