package com.qnadeel.springdemo.security.management.repositories;

import com.qnadeel.springdemo.security.management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    User getUserByUserName(String userName);

    Optional<User> findByUserId(Long userId);

    User getByUserId(Long userId);

    boolean existsByUserName(String userName);

    boolean existsByUserEmail(String userEmail);

    Optional<User> findByUserName(String userName);
}