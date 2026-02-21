package com.ecse428.WaitForDinner.dto;

import java.util.List;

public class PreferenceUpdateRequestDto {
  private List<Integer> preferenceTypeIds;

  public PreferenceUpdateRequestDto() {}

  public List<Integer> getPreferenceTypeIds() {
    return preferenceTypeIds;
  }

  public void setPreferenceTypeIds(List<Integer> preferenceTypeIds) {
    this.preferenceTypeIds = preferenceTypeIds;
  }
}