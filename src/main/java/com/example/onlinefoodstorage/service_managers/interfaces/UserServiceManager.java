package com.example.onlinefoodstorage.service_managers.interfaces;

import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.dtos.users.AuthenticationResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceManager extends CrudServiceManager<ResponseEntity<UserResponse>, UserRequest, Integer> {
    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest);

    ResponseEntity<UserResponse> getUserById(String id);

    ResponseEntity<PagingResponse<UserResponse>> findByEmployeeId(int page, int size, Integer employeeId);
}
