package com.example.onlinefoodstorage.mappers;

import com.example.onlinefoodstorage.annotations.Mapper;
import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.enums.Role;
import com.example.onlinefoodstorage.enums.Status;
import com.example.onlinefoodstorage.mappers.interfaces.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .role(userRequest.getRole())
                .password(userRequest.getPassword())
                .createdTime(LocalDateTime.now())
                .username(userRequest.getUsername())
                .status(Status.ACTIVE).build();
    }

    @Override
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .status(user.getStatus().toString())
                .password(user.getPassword())
                .username(user.getUsername())
                .role(user.getRole().name())
                .createdTime(user.getCreatedTime().toString())
                .employeeId(user.getEmployeeId()).build();
    }

    @Override
    public PagingResponse<UserResponse> toResponse(Page<User> user) {
        List<UserResponse> list = user.stream().map(this::toResponse).toList();
        return new PagingResponse<>(list, user.getTotalElements(), user.getTotalPages(),user.hasContent());
    }
}
