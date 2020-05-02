package com.back_end.project_management_system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.back_end.project_management_system.dao.UserDAO;
import com.back_end.project_management_system.dao.UserDetailsDAO;
import com.back_end.project_management_system.dto.UpdateUserDTO;
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
	
	@Transactional
	public UserDetails getUserByJwtToken(String token) {
		
		String username = jwtUtil.getUsernameFromToken(token);
		
		UserDetails userDetails = validUser(username);
		
		return userDetails;
	}
	
	@Transactional
	public List<UserDetails> getAllUsers() {
	
		return userDAO.getAllUsers();
	}
	
	@Transactional
	public List<UserDetails> getAllAdminsManagers() {
		
		return userDAO.getAllAdminsManagers();
	}
	
	@Transactional
	public UserDetails updateUser(UpdateUserDTO updateUserDTO, @PathVariable String username) {
		
		User user = getUserByUsername(username);
		
		user.setRole(updateUserDTO.getRole());
		user.getUserDetails().setRole(updateUserDTO.getRole());
		user.getUserDetails().setName(updateUserDTO.getName());
		
		userDAO.saveUser(user);
		
		return user.getUserDetails();
	}
	
	@Transactional
	public Map<String, String> deleteUser(String username) {
		
		User user = getUserByUsername(username);
		
		userDAO.deleteUser(user);
		
		Map<String, String> response = new HashMap<String, String>();
		
		response.put("username", username);
		
		return response;
	}
	
	@Transactional
	public User getUserByUsername(String username) {
		
		Optional<User> user = userDAO.getUserByUsername(username);
		
		if (!user.isPresent()) {
			throw new AuthException("User doesn't exist with username: " + username);
		}
		
		return user.get();
	}
	
	@Transactional
	public UserDetails validUser(String username) {
		
		return userDAO.validUser(username);
		
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
