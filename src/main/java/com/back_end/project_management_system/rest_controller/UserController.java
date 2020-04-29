package com.back_end.project_management_system.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/token/{token}")
	public ResponseEntity<?> getUserByJwtToken(@PathVariable String token) {
		
		UserDetails user = userService.getUserByJwtToken(token);
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/projectLead")
	public ResponseEntity<?> getAllAdminsManagers() {
	
		List<UserDetails> userDetails = userService.getAllAdminsManagers();
		
		return ResponseEntity.ok(userDetails);
	}
	
	@GetMapping("")
	public List<UserDetails> getAllUsers() {
		return userService.getAllUsers();
	}

}
