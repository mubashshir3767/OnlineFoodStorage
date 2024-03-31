package com.example.onlinefoodstorage.dtos.users;

import com.example.onlinefoodstorage.enums.Role;
import com.example.onlinefoodstorage.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Integer id;

    private Role role;

    private String name;

    private String username;

    private String password;

    private Integer employeeId;
}
