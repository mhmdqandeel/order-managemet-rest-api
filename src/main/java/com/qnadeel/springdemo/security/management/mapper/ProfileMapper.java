package com.qnadeel.springdemo.security.management.mapper;

import com.qnadeel.springdemo.security.management.dto.ProfileDTO;
import com.qnadeel.springdemo.security.management.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Builder
@Data
@AllArgsConstructor
@Component
public class ProfileMapper {

    public ProfileDTO profileToProfileDTO(Profile profile) {

        return ProfileDTO.builder()
                .userId(profile.getUser().getUserId())
                .bio(profile.getBio())
                .dateOfBirth(profile.getDateOfBirth())
                .phoneNumber(profile.getPhoneNumber())
                .build();
    }
}
