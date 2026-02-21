package com.ecse428.WaitForDinner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecse428.WaitForDinner.dto.PreferenceUpdateRequestDto;
import com.ecse428.WaitForDinner.dto.PreferenceUpdateResponseDto;
import com.ecse428.WaitForDinner.service.AuthService;
import com.ecse428.WaitForDinner.service.PreferenceService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

  private final PreferenceService preferenceService;
  private final AuthService authService;

  public PreferenceController(
      PreferenceService preferenceService,
      AuthService authService) {
    this.preferenceService = preferenceService;
    this.authService = authService;
  }

  @PostMapping("/update")
  public ResponseEntity<PreferenceUpdateResponseDto> updatePreferences(
      @RequestBody PreferenceUpdateRequestDto req,
      HttpSession session) {

    int userId = authService.requireLoggedInUserId(session);
    return ResponseEntity.ok(
        preferenceService.updatePreferences(req, userId));
  }
}