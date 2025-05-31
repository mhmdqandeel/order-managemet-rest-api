package com.qnadeel.springdemo.security.management.controller;

import com.qnadeel.springdemo.security.management.dto.request.CreateAccountRequest;
import com.qnadeel.springdemo.security.management.dto.response.UserResponse;
import com.qnadeel.springdemo.security.management.mapper.UserMapper;
import com.qnadeel.springdemo.security.management.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users") // class level
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper
                .userToUserResponse(userService
                        .getUserByID(userId)));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> editUser(@PathVariable Long userId,
                                                 @RequestBody CreateAccountRequest updateRequest){
        return ResponseEntity.ok(userService.updateUser(userId,updateRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/user/{userId}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long userId,
                                                 @RequestParam String newPassword) {
        userService.updatePassword(userId,newPassword);
        return ResponseEntity.ok("Password changed successfully");
    }

    @PutMapping("/{userId}/deactivate")
    public ResponseEntity<String> deactivateUser(@PathVariable Long userId) {
        userService.deactivateUser(userId);
        return ResponseEntity.ok("User deactivated successfully");
    }
}