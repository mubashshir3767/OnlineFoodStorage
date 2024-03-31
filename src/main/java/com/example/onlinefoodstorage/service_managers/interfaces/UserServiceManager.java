package com.example.onlinefoodstorage.service_managers.interfaces;

import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.dtos.users.AuthenticationResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserServiceManager extends CrudServiceManager<ResponseEntity<UserResponse>, UserRequest,Integer> {
  ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest);
}
