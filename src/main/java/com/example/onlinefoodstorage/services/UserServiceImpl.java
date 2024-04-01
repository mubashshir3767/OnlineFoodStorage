package com.example.onlinefoodstorage.services;

import com.example.onlinefoodstorage.dtos.users.AuthenticationRequest;
import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.enums.Role;
import com.example.onlinefoodstorage.enums.Status;
import com.example.onlinefoodstorage.repos.UserRepo;
import com.example.onlinefoodstorage.services.interfaces.UserService;
import com.example.onlinefoodstorage.utils.OptionalEntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return userRepo.save(entity);
    }

    @Override
    @Cacheable(value = "users",key = "#id")
    public User getById(Integer id) {
        Optional<User> optionalUser = this.findById(id);
        return OptionalEntityUtil.getIfPresent(optionalUser);
    }

    @Override
    @Caching(put = @CachePut(value = "users", key = "#entity.id"),
            evict = @CacheEvict(value = "users", key = "'list'"))
    public User update(User entity) {
        return userRepo.save(entity);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "users", key = "'list'"),
            @CacheEvict(value = "users", key = "#id")
    })
    public void delete(Integer id) {
        User user = this.getById(id);
        userRepo.delete(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public Page<User> findByEmployeeId(Integer employeeId, Pageable pageable) {
        return userRepo.findByEmployeeId(employeeId,pageable);
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
