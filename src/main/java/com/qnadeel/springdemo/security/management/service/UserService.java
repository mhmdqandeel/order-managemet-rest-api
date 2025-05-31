package com.qnadeel.springdemo.security.management.service;

import com.qnadeel.springdemo.security.management.dto.request.CreateAccountRequest;
import com.qnadeel.springdemo.security.management.dto.request.LoginRequest;
import com.qnadeel.springdemo.security.management.dto.response.UserResponse;
import com.qnadeel.springdemo.security.management.entities.Profile;
import com.qnadeel.springdemo.security.management.entities.User;
import com.qnadeel.springdemo.security.management.exeption.ResourcesNotFoundException;
import com.qnadeel.springdemo.security.management.mapper.ProfileMapper;
import com.qnadeel.springdemo.security.management.mapper.UserMapper;
import com.qnadeel.springdemo.security.management.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ProfileService profileService;

    @Transactional
    public void creteAccount(CreateAccountRequest createAccountRequest) {

        if (userRepository.existsByUserName(createAccountRequest.getUserName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already in use");
        }

        if (userRepository.existsByUserEmail(createAccountRequest.getUserEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already taken");
        }

        User user = new User();

        user.setUserName(createAccountRequest.getUserName());
        user.setUserPassword(passwordEncoder.encode(createAccountRequest.getPassword()));
        user.setUserEmail(createAccountRequest.getUserEmail());

        Profile profile = Profile.builder()
                        .bio("")
                                .dateOfBirth(null)
                                        .phoneNumber("")
                                                        .user(user)
                                                                .build();

        user.setProfile(profile);

        profileService.save(profile);

        userRepository.save(user);
    }

    public void userLogin(LoginRequest loginRequest) {
        User user = userRepository.getUserByUserName(loginRequest.getUserName());

        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!(passwordEncoder.matches(loginRequest.getPassword(), user.getUserPassword()))) {
            throw new BadCredentialsException("Wrong password");
        }
    }

    public Optional<User> findUserByUserName(String username) {

        return userRepository.findByUserName(username);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream().map(userMapper::userToUserResponse)
                .toList();
    }

    public User getUserByID(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourcesNotFoundException("User with ID " + userId + " not found"));

        if (!(user.isActive())){
            throw new ResourcesNotFoundException("User with ID " + userId + " is deactivated");
        }
        return user;
    }

    public UserResponse updateUser(Long userID ,CreateAccountRequest updateRequest) {

        User user = getUserByID(userID);

        user.setUserPassword(passwordEncoder.encode(updateRequest.getPassword()));
        user.setUserEmail(updateRequest.getUserEmail());
        user.setUserName(updateRequest.getUserName());

        return userMapper.userToUserResponse(userRepository.save(user));
    }

    public void deleteUser(Long userId) {

        User user = getUserByID(userId);

        userRepository.delete(user);
    }

    public void updatePassword(Long userId, String newPassword) {

        User user = getUserByID(userId);

        user.setUserPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }

    public void deactivateUser(Long userId) {

        User user = getUserByID(userId);

        user.setActive(false);

        userRepository.save(user);
    }

}
