package com.example.onlinefoodstorage.repos;

import com.example.onlinefoodstorage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String currentUserUsername);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
