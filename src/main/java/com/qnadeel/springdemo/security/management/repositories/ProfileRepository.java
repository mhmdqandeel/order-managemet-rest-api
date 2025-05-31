package com.qnadeel.springdemo.security.management.repositories;

import com.qnadeel.springdemo.security.management.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByProfileId(Long profileID);
}
