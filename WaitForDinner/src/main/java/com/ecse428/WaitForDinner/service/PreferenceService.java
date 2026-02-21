package com.ecse428.WaitForDinner.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecse428.WaitForDinner.dto.PreferenceUpdateRequestDto;
import com.ecse428.WaitForDinner.dto.PreferenceUpdateResponseDto;
import com.ecse428.WaitForDinner.model.PreferenceType;
import com.ecse428.WaitForDinner.model.User;
import com.ecse428.WaitForDinner.repository.PreferenceTypeRepository;
import com.ecse428.WaitForDinner.repository.UserRepository;

@Service
public class PreferenceService {

  private final UserRepository userRepository;
  private final PreferenceTypeRepository preferenceTypeRepository;

  public PreferenceService(
      UserRepository userRepository,
      PreferenceTypeRepository preferenceTypeRepository) {
    this.userRepository = userRepository;
    this.preferenceTypeRepository = preferenceTypeRepository;
  }

  public PreferenceUpdateResponseDto updatePreferences(
      PreferenceUpdateRequestDto req,
      int userId) {

    User user = userRepository.findById(userId)
        .orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    // 1. Remove all existing preferences
    List<PreferenceType> existing = new ArrayList<>(user.getPreferenceTypes());
    for (PreferenceType p : existing) {
      user.removePreferenceType(p);
    }

    // 2. Add selected preferences
    List<String> updatedNames = new ArrayList<>();

    for (Integer prefId : req.getPreferenceTypeIds()) {
      PreferenceType pref = preferenceTypeRepository.findById(prefId)
          .orElseThrow(() ->
              new ResponseStatusException(
                  HttpStatus.BAD_REQUEST,
                  "PreferenceType not found: " + prefId));

      user.addPreferenceType(pref);
      updatedNames.add(pref.getName());
    }

    return new PreferenceUpdateResponseDto(updatedNames);
  }
}