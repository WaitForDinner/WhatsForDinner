package com.ecse428.WaitForDinner.dto;

import java.util.List;

public class PreferenceUpdateResponseDto {
  private List<String> preferences;

  public PreferenceUpdateResponseDto(List<String> preferences) {
    this.preferences = preferences;
  }

  public List<String> getPreferences() {
    return preferences;
  }
}