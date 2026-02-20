package com.ecse428.WaitForDinner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecse428.WaitForDinner.dto.ProfileUpdateRequestDto;
import com.ecse428.WaitForDinner.dto.ProfileUpdateResponseDto;
import com.ecse428.WaitForDinner.service.AuthService;
import com.ecse428.WaitForDinner.service.ProfileService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final AuthService authService;

    public ProfileController(ProfileService profileService, AuthService authService) {
        this.profileService = profileService;
        this.authService = authService;
    }

    @PostMapping("/update")
    public ResponseEntity<ProfileUpdateResponseDto> updateProfile(@RequestBody ProfileUpdateRequestDto req, HttpSession session) {
        if (req.getEmail() == null || req.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        } else if (req.getName() == null || req.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
        }
        
        int userID = authService.requireLoggedInUserId(session);

        return ResponseEntity.ok(profileService.UpdateProfile(req, userID));
    }
}
