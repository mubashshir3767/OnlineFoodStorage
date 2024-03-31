package com.example.onlinefoodstorage.service_managers;

import com.example.onlinefoodstorage.annotations.ServiceManager;
import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.dtos.users.AuthenticationResponse;
import com.example.onlinefoodstorage.dtos.users.UserRequest;
import com.example.onlinefoodstorage.dtos.users.UserResponse;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.mappers.interfaces.UserMapper;
import com.example.onlinefoodstorage.security.JwtService;
import com.example.onlinefoodstorage.service_managers.interfaces.UserServiceManager;
import com.example.onlinefoodstorage.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


@ServiceManager
@RequiredArgsConstructor
public class UserServiceManagerImpl implements UserServiceManager {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<UserResponse> create(UserRequest request) {
        User entity = userMapper.toEntity(request);
        User employee = userService.getUserById(request.getEmployeeId().toString());
        entity.setEmployeeId(employee.getEmployeeId());
        userService.create(entity);
        return ResponseEntity.ok(userMapper.toResponse(entity));
    }

    @Override
    public ResponseEntity<UserResponse> update(UserRequest request) {
        User entity = userMapper.toEntity(request);
        User employee = userService.getUserById(request.getEmployeeId().toString());
        entity.setEmployeeId(employee.getEmployeeId());
        entity.setId(request.getId());
        userService.create(entity);
        return ResponseEntity.ok(userMapper.toResponse(entity));

    }

    @Override
    public ResponseEntity<UserResponse> getById(String integer) {
        return null;
    }

    @Override
    public void delete(String integer) {
        userService.delete(integer);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
        User user = userService.getByUsernameAndPassword(authenticationRequest);
        String token = jwtService.generateToken(user);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(new AuthenticationResponse(userResponse, token));
    }

    @Override
    public String getUserById(String id) {
        User user = userService.getUserById(id);
        return user.toString();
    }
}
