package com.ecse428.WaitForDinner.service;

import com.ecse428.WaitForDinner.dto.LoginRequestDto;
import com.ecse428.WaitForDinner.dto.LoginResponseDto;
import com.ecse428.WaitForDinner.model.User;
import com.ecse428.WaitForDinner.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

	public static final String SESSION_USER_ID = "userId";

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public LoginResponseDto login(LoginRequestDto request, HttpSession session) {
		if (request.getEmail() == null || request.getEmail().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
		}
		if (request.getPassword() == null || request.getPassword().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
		}

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
		}

		session.setAttribute(SESSION_USER_ID, user.getUserId());
		return new LoginResponseDto(user.getUserId(), user.getName(), user.getEmail());
	}

	public void logout(HttpSession session) {
		session.invalidate();
	}

	public int requireLoggedInUserId(HttpSession session) {
		Integer userId = (Integer) session.getAttribute(SESSION_USER_ID);
		if (userId == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not logged in");
		}
		return userId;
	}
}
