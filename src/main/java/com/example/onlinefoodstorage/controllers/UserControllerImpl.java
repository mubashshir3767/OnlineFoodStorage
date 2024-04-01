package com.example.onlinefoodstorage.controllers;

import com.example.onlinefoodstorage.controllers.interfaces.UserController;
import com.example.onlinefoodstorage.dtos.PagingResponse;
import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.dtos.users.AuthenticationResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.service_managers.interfaces.UserServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin-panel")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserServiceManager userServiceManager;

    @Override
    @PostMapping("save")
    public ResponseEntity<UserResponse> create(UserRequest request) {
        return userServiceManager.create(request);
    }

    @Override
    @GetMapping("getById/{id}")
    public ResponseEntity<UserResponse> getById(String id) {
        return userServiceManager.getUserById(id);
    }

    @Override
    @PutMapping("update")
    public ResponseEntity<UserResponse> update(UserRequest request) {
        return userServiceManager.update(request);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(Integer id) {
        userServiceManager.delete(id);
        return ResponseEntity.ok("OBJECT HAS BEEN SUCCESSFULLY DELETED");
    }

    @Override
    @GetMapping("user-list")
    public ResponseEntity<PagingResponse<UserResponse>> getUserByEmployeeId(int page, int size, Integer employeeID) {
        return userServiceManager.findByEmployeeId(page, size, employeeID);
    }

    @Override
    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        return userServiceManager.login(request);
    }

}
