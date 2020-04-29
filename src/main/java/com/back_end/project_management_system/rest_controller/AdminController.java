package com.back_end.project_management_system.rest_controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.project_management_system.dto.UpdateUserDTO;
import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@PutMapping("/users/{username}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO, @PathVariable String username) {
	
		UserDetails userDetails = userService.updateUser(updateUserDTO, username);
		
		return ResponseEntity.ok(userDetails);
	}
	
	@DeleteMapping("/users/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		
		Map<String, String> response = userService.deleteUser(username);
		
		return ResponseEntity.ok(response);
	}

}
