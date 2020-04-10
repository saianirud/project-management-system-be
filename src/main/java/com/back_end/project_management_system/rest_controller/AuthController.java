package com.back_end.project_management_system.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.project_management_system.entity.SecurityQuestion;
import com.back_end.project_management_system.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;

	@PostMapping("/signup")
	public String userRegistration() {
		
		return authService.userRegistration();
	}
	
	@GetMapping("/securityQuestions")
	public List<SecurityQuestion> getSecurityQuestions() {
		
		return authService.getSecurityQuestions();
	}
}
