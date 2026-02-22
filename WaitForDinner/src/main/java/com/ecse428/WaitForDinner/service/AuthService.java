package com.ecse428.WaitForDinner.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.ecse428.WaitForDinner.dto.LoginRequestDto;
import com.ecse428.WaitForDinner.dto.LoginResponseDto;
import com.ecse428.WaitForDinner.model.User;
import com.ecse428.WaitForDinner.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

	public static final String SESSION_USER_ID = "userId";

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Utility function to check if the provided email. There is no username field in the user 
	 * database table, so I did not implement it for username.
	 *
	 * @author Bilguun Tegshbayar
	 * @param request A JSON object containing "email".
	 * @return A JSON object with keys "emailUnique",
	 *         each mapped to a boolean indicating uniqueness.
	 *         Example response: { "emailUnique": true }
	 */
	public ResponseEntity<Map<String, Boolean>> checkUnique(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		boolean emailUnique = userRepository.findByEmail(email).isEmpty();
		Map<String, Boolean> result = new HashMap<>();
		result.put("emailUnique", emailUnique);
		return new ResponseEntity<>(result, HttpStatus.OK);
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
}
