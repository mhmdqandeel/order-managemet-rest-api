package com.qnadeel.springdemo.security.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {

    private long userId;

    private String bio;

    private String phoneNumber;

    private LocalDate dateOfBirth;

}
