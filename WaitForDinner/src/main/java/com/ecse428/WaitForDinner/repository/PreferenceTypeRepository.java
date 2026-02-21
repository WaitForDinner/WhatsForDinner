package com.ecse428.WaitForDinner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecse428.WaitForDinner.model.PreferenceType;

public interface PreferenceTypeRepository
    extends JpaRepository<PreferenceType, Integer> {
}