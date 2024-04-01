package com.example.onlinefoodstorage.service_managers;

import com.example.onlinefoodstorage.annotations.ServiceManager;
import com.example.onlinefoodstorage.dtos.PagingResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        User employee = userService.getCurrentUser();
        entity.setEmployeeId(employee.getEmployeeId());
        userService.create(entity);
        return ResponseEntity.ok(userMapper.toResponse(entity));
    }

    @Override
    public ResponseEntity<UserResponse> update(UserRequest request) {
        User entity = userMapper.toEntity(request);
        User employee = userService.getCurrentUser();
        entity.setEmployeeId(employee.getEmployeeId());
        entity.setId(request.getId());
        userService.create(entity);
        return ResponseEntity.ok(userMapper.toResponse(entity));
    }

    @Override
    public ResponseEntity<UserResponse> getById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {
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
    public ResponseEntity<UserResponse> getUserById(String id) {
        User user = userService.getById(Integer.parseInt(id));
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @Override
    public ResponseEntity<PagingResponse<UserResponse>> findByEmployeeId(int page, int size, Integer employeeId) {
        Page<User> userPage = userService.findByEmployeeId(employeeId, PageRequest.of(page, size));
        PagingResponse<UserResponse> response = userMapper.toResponse(userPage);
        return ResponseEntity.ok(response);
    }
}
