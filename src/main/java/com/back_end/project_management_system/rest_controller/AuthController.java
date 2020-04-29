package com.back_end.project_management_system.rest_controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.project_management_system.dto.ForgotPasswordDTO;
import com.back_end.project_management_system.dto.RegisterUserDTO;
import com.back_end.project_management_system.dto.ResetPasswordDTO;
import com.back_end.project_management_system.entity.SecurityQuestion;
import com.back_end.project_management_system.entity.User;
import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.jwt.AuthenticationRequest;
import com.back_end.project_management_system.jwt.JWTUtil;
import com.back_end.project_management_system.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public ResponseEntity<?> userRegistration(@Valid @RequestBody RegisterUserDTO registrationData) {
		
		UserDetails newUser = authService.userRegistration(registrationData);
		
		return ResponseEntity.ok(newUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		Map<String, Object> response = authService.createAuthenticationToken(authenticationRequest);

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> getForgotPasswordToken(@Valid @RequestBody ForgotPasswordDTO req) {
		
		String token = authService.getForgotPasswordToken(req);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("passwordResetLink", "http://localhost:4200/reset-password/" + token);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/resetPassword/{token}")
	public ResponseEntity<?> getForgotPasswordForm(@PathVariable String token) {
		
		User user = authService.validateUserByToken(token);

		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordDTO req) {
		
		User user = authService.validateUserByToken(req.getToken());
		
		boolean reset = authService.resetPassword(user, req.getPassword());
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", "Password reset successful!");
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/securityQuestions")
	public List<SecurityQuestion> getSecurityQuestions() {
		
		return authService.getSecurityQuestions();
	}
}
