package com.example.onlinefoodstorage.dtos.users;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private String id;

    private String name;

    private String status;

    private String username;

    private String password;

    private String role;

    private String createdTime;

    private Integer employeeId;
}
