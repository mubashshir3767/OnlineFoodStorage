package com.example.onlinefoodstorage.repos;

import com.example.onlinefoodstorage.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String currentUserUsername);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Page<User> findByEmployeeId(Integer employeeId, Pageable pageable);
}
