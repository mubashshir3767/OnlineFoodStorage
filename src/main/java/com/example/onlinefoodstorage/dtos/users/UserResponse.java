package com.example.onlinefoodstorage.dtos.users;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;

    private String name;

    private String status;

    private String username;

    private String password;

    private String role;

    private String createdTime;

    private Integer employeeId;
}
