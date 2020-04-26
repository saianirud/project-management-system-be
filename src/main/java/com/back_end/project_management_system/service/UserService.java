package com.back_end.project_management_system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back_end.project_management_system.dao.UserDAO;
import com.back_end.project_management_system.dao.UserDetailsDAO;
import com.back_end.project_management_system.entity.User;
import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.exception.AuthException;
import com.back_end.project_management_system.exception.ProjectException;
import com.back_end.project_management_system.jwt.JWTUtil;

@Service
public class UserService {
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	public Map<String, Object> getUserByJwtToken(String token) {
		
		String username = jwtUtil.getUsernameFromToken(token);
		
		Optional<User> user = userDAO.getUserByUsername(username);
		
		Map<String, Object> modifiedUser = new HashMap<String, Object>();
		
		modifiedUser.put("username", user.get().getUsername());
		modifiedUser.put("name", user.get().getUserDetails().getName());
		modifiedUser.put("token", token);
		modifiedUser.put("role", user.get().getRole());
		
		return modifiedUser;
	}
	
	public List<UserDetails> getAllUsers() {
	
		return userDAO.getAllUsers();
	}
	
	public List<UserDetails> getAllAdminsManagers() {
		
		return userDAO.getAllAdminsManagers();
	}
	
	public UserDetails validUser(String username) {
		
		Optional<UserDetails> user = userDetailsDAO.getUserByUsername(username);
		
		if (!user.isPresent()) {
			throw new AuthException("User doesn't exist with username: " + username);
		}
		
		return user.get();
		
	}
	
	public boolean isAuthorizedAdminOrManager(String jwtToken, String projectLead) {
		
		String username = jwtUtil.getUsernameFromToken(jwtToken);
		
		UserDetails userDetails = validUser(username);
		
		if (userDetails.getRole().equals("MANAGER")) {
			if (!userDetails.getUsername().equals(projectLead)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	public UserDetails isAdminOrManager(String username) {
		
		UserDetails userDetails = validUser(username);
		
		if (userDetails.getRole().equals("ADMIN") || userDetails.getRole().equals("MANAGER")) {
			return userDetails;
		}
		
		throw new ProjectException("Can't assign to " + username);
	}
	
	public boolean isAdmin(String username) {
		
		UserDetails userDetails = validUser(username);
		
		if (userDetails.getRole().equals("ADMIN")) {
			return true;
		}
		
		return false;
	}

}
