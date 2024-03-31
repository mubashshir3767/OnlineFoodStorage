package com.example.onlinefoodstorage.services;

import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.repos.UserRepo;
import com.example.onlinefoodstorage.services.interfaces.UserService;
import com.example.onlinefoodstorage.utils.OptionalEntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User create(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public User getById(Integer integer) {
        return null;
    }

    @Cacheable(value = "users", key = "#id")
    @Override
    public User getUserById(String id) {
        Optional<User> optionalUser = this.findById(Integer.parseInt(id));
        return OptionalEntityUtil.getIfPresent(optionalUser);
    }

    @Override
    @Caching(put = @CachePut(value = "users", key = "#id"),
            evict = @CacheEvict(value = "users", key = "'list'"))
    public User update(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Caching(evict = {
            @CacheEvict(value = "users", key = "'list'"),
            @CacheEvict(value = "users", key = "#id")
    })
    public void delete(String id) {
        User user = this.getUserById(id);
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
