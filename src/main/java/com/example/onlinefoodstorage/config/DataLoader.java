package com.example.onlinefoodstorage.config;

import com.example.onlinefoodstorage.entities.User;
import com.example.onlinefoodstorage.enums.Role;
import com.example.onlinefoodstorage.enums.Status;
import com.example.onlinefoodstorage.security.JwtService;
import com.example.onlinefoodstorage.services.interfaces.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final JwtService jwtService;

    public DataLoader(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
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

        String token = jwtService.generateToken(user);
        System.out.println(token);
        Claims claims = jwtService.getClaims(token);
        List<String> author = (List<String>) claims.get("authorities");
        List<SimpleGrantedAuthority> grantedAuthorities = author.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
