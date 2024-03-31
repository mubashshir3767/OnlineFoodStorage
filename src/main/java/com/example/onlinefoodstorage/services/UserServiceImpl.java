package com.example.onlinefoodstorage.services;

import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.enums.Role;
import com.example.onlinefoodstorage.enums.Status;
import com.example.onlinefoodstorage.repos.UserRepo;
import com.example.onlinefoodstorage.services.interfaces.UserService;
import com.example.onlinefoodstorage.utils.OptionalEntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User create(User entity) {
        entity.setStatus(Status.ACTIVE);
        entity.setCreatedTime(LocalDateTime.now());
        entity.setRole(Role.USER);
        return userRepo.save(entity);
    }

    @Override
    @Cacheable(value = "myCache", key = "'user_'+#id")
    public User getById(Integer id) {
        Optional<User> optionalUser = this.findById(id);
        return OptionalEntityUtil.getIfPresent(optionalUser);
    }

    @Override
    public User update(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public void delete(Integer id) {
        User user = this.getById(id);
        userRepo.delete(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public User getCurrentUser() {
        String currentUserUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.getByUsername(currentUserUsername);
    }

    @Override
    public User getByUsername(String currentUserUsername) {
        Optional<User> optionalUser = userRepo.findByUsername(currentUserUsername);
        return OptionalEntityUtil.getIfPresent(optionalUser);
    }

    @Override
    public User getByUsernameAndPassword(AuthenticationRequest request) {
        Optional<User> optionalUser =
                userRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        return OptionalEntityUtil.getIfPresent(optionalUser);
    }
}
