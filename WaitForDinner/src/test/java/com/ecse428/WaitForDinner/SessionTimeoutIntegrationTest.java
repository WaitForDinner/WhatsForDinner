package com.ecse428.WaitForDinner;

import com.ecse428.WaitForDinner.model.User;
import com.ecse428.WaitForDinner.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SessionTimeoutIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String TEST_EMAIL = "timeout.test@example.com";
	private static final String TEST_PASSWORD = "SecurePass1!";

	@BeforeEach
	void setUp() {
		if (userRepository.findByEmail(TEST_EMAIL).isEmpty()) {
			User user = new User("Timeout User", TEST_EMAIL, passwordEncoder.encode(TEST_PASSWORD), Date.valueOf("2025-01-01"), 0);
			userRepository.save(user);
		}
	}

	@Test
	void testValidLoginCreatesSession() throws Exception {
		String body = objectMapper.writeValueAsString(Map.of("email", TEST_EMAIL, "password", TEST_PASSWORD));

		mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.email").value(TEST_EMAIL));
	}

	@Test
	void testInvalidSessionReturnsUnauthorized() throws Exception {
		MockHttpSession invalidSession = new MockHttpSession();
		invalidSession.invalidate();

		mockMvc.perform(post("/api/auth/logout")
				.session(invalidSession))
			.andExpect(status().isUnauthorized());
	}

	@Test
	void testExpiredSessionIsRejected() throws Exception {
		String body = objectMapper.writeValueAsString(Map.of("email", TEST_EMAIL, "password", TEST_PASSWORD));

		MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
			.andExpect(status().isOk())
			.andReturn();

		MockHttpSession session = (MockHttpSession) loginResult.getRequest().getSession(false);
		session.invalidate();

		mockMvc.perform(post("/api/auth/logout")
				.session(session))
			.andExpect(status().isUnauthorized());
	}
}
