package com.example.onlinefoodstorage.mappers.interfaces;


import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.entities.User;

public interface UserMapper {
    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);
}
