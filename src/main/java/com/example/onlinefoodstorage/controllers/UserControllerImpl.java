package com.example.onlinefoodstorage.controllers;

import com.example.onlinefoodstorage.controllers.interfaces.UserController;
import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.dtos.users.AuthenticationResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.service_managers.interfaces.UserServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    @GetMapping("getById/{id}")
    @Cacheable(value = "users", key = "#id")
    public String getById(String id) {
        return userServiceManager.getUserById(id);
    }

    @Override
    @PutMapping("update")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> update(UserRequest request) {
        return userServiceManager.update(request);
    }

    @Override
    @DeleteMapping("delete/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> delete(String id) {
        userServiceManager.delete(id);
        return ResponseEntity.ok("OBJECT HAS BEEN SUCCESSFULLY DELETED");
    }

    @Override
    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        return userServiceManager.login(request);
    }

    @Cacheable(value = "users", key = "'list'")
    @GetMapping("/my-users")
    public List<String> users() {
        System.out.println("my-users: " + new Date());
        return List.of("user1", "user2");
    }

}
