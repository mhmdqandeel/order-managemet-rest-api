package com.qnadeel.springdemo.security.management.service;

import com.qnadeel.springdemo.security.management.dto.ProfileDTO;
import com.qnadeel.springdemo.security.management.entities.Profile;
import com.qnadeel.springdemo.security.management.entities.User;
import com.qnadeel.springdemo.security.management.repositories.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final UserService userService;

    public ProfileService(ProfileRepository profileRepository,@Lazy UserService userService) {
        this.profileRepository = profileRepository;
        this.userService = userService;
    }


    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    public Profile updateProfileInfo(ProfileDTO profile) {

        User user = userService.getUserByID(profile.getUserId());

        Profile updatedProfile = user.getProfile();

        updatedProfile.setBio(profile.getBio());
        updatedProfile.setPhoneNumber(profile.getPhoneNumber());
        updatedProfile.setDateOfBirth(profile.getDateOfBirth());

        return profileRepository.save(updatedProfile);
    }

    @Transactional
    public void deleteProfile(Long profileID) {

        Profile profile = profileRepository.findByProfileId(profileID)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        userService.deleteUser(profile.getUser().getUserId());

        profileRepository.delete(profile);
    }
}
