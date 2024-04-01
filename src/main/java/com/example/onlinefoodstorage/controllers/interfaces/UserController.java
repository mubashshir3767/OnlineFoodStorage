package com.example.onlinefoodstorage.controllers.interfaces;

import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.dtos.users.AuthenticationResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {
    ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request);

    ResponseEntity<UserResponse> getById(@PathVariable String id);

    ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest request);

    ResponseEntity<String> delete(@PathVariable Integer id);

    ResponseEntity<PagingResponse<UserResponse>> getUserByEmployeeId(@RequestParam(required = false, defaultValue = "0") int page,
                                                                     @RequestParam(required = false, defaultValue = "10") int size,
                                                                     @RequestParam(required = false) Integer employeeID);

    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request);

}
