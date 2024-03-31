package com.example.onlinefoodstorage.controllers.interfaces;

import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.dtos.users.AuthenticationResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request);

    ResponseEntity<UserResponse> getById(@PathVariable Integer id);

    ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest request);

    ResponseEntity<String> delete(@PathVariable Integer id);

    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request);

}
