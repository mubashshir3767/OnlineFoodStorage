package com.example.onlinefoodstorage.services.interfaces;

import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.entities.User;

import java.util.Optional;

public interface UserService extends CrudService<User, Integer, User, User> {
    Optional<User> findById(Integer id);

    User getCurrentUser();

    User getByUsername(String currentUserUsername);

    User getByUsernameAndPassword(AuthenticationRequest authenticationRequest);
}
