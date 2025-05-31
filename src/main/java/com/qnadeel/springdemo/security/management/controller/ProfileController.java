package com.qnadeel.springdemo.security.management.controller;

import com.qnadeel.springdemo.security.management.dto.ProfileDTO;
import com.qnadeel.springdemo.security.management.mapper.ProfileMapper;
import com.qnadeel.springdemo.security.management.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    @PostMapping("/")
    public ResponseEntity<ProfileDTO> updateProfileInfo(@RequestBody ProfileDTO profile) {

        return ResponseEntity
                .ok(profileMapper
                        .profileToProfileDTO(profileService
                                .updateProfileInfo(profile)));

    }

    @DeleteMapping("/{profileID}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long profileID) {
        profileService.deleteProfile(profileID);
        return ResponseEntity.ok("Deleted profile " + profileID);
    }
}
