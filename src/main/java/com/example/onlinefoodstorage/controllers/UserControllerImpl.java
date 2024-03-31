package com.example.onlinefoodstorage.controllers;

import com.example.onlinefoodstorage.controllers.interfaces.UserController;
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
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> create(UserRequest request) {
        return userServiceManager.create(request);
    }

    @Override
    @GetMapping("getById/id")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> getById(Integer id) {
        return userServiceManager.getById(id);
    }

    @Override
    @PutMapping("update")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> update(UserRequest request) {
        return userServiceManager.update(request);
    }

    @Override
    @DeleteMapping("delete/id")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> delete(Integer id) {
        userServiceManager.delete(id);
        return ResponseEntity.ok("OBJECT HAS BEEN SUCCESSFULLY DELETED");
    }

    @Override
    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        return userServiceManager.login(request);
    }
}
