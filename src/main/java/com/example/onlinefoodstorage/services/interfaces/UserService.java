package com.example.onlinefoodstorage.services.interfaces;

import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService extends CrudService<User, Integer, User, User> {
    Optional<User> findById(Integer id);

    Page<User> findByEmployeeId(Integer employeeId, Pageable pageable);

    User getCurrentUser();

    User getByUsername(String currentUserUsername);

    User getByUsernameAndPassword(AuthenticationRequest authenticationRequest);
}
