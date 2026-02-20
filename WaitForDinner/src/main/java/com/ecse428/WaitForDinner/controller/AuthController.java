package com.ecse428.WaitForDinner.controller;

import com.ecse428.WaitForDinner.dto.LoginRequestDto;
import com.ecse428.WaitForDinner.dto.LoginResponseDto;
import com.ecse428.WaitForDinner.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request, HttpSession session) {
		return ResponseEntity.ok(authService.login(request, session));
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		authService.logout(session);
		return ResponseEntity.noContent().build();
	}
}
