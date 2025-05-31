package com.qnadeel.springdemo.security.management.mapper;

import com.qnadeel.springdemo.security.management.dto.response.UserResponse;
import com.qnadeel.springdemo.security.management.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse userToUserResponse(User user);
}