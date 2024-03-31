package com.example.onlinefoodstorage.config;

import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.enums.Role;
import com.example.onlinefoodstorage.enums.Status;
import com.example.onlinefoodstorage.services.interfaces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setStatus(Status.ACTIVE);
        user.setCreatedTime(LocalDateTime.now());
        user.setName("admin3");
        user.setPassword("admin1");
        user.setUsername("admin2");
        user.setEmployeeId(0);
        userService.create(user);
    }
}
