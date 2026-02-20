package com.ecse428.WaitForDinner.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecse428.WaitForDinner.dto.ProfileUpdateRequestDto;
import com.ecse428.WaitForDinner.dto.ProfileUpdateResponseDto;
import com.ecse428.WaitForDinner.model.User;
import com.ecse428.WaitForDinner.repository.UserRepository;

@Service
public class ProfileService {
    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileUpdateResponseDto UpdateProfile(ProfileUpdateRequestDto req, int userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String newEmail = req.getEmail();
        String newName = req.getName();
        
        user.setEmail(newEmail);
        user.setName(newName);

        ProfileUpdateResponseDto res = new ProfileUpdateResponseDto();
        res.setEmail(newEmail);
        res.setName(newName);

        return res;
    }
}
