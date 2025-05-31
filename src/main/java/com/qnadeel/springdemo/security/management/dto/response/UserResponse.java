package com.qnadeel.springdemo.security.management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private long userId;

    private String userName;

    private String userEmail;

    private boolean isActive;
}