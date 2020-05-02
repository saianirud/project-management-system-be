package com.back_end.project_management_system.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.back_end.project_management_system.dao.AuthDAO;
import com.back_end.project_management_system.dao.UserDAO;
import com.back_end.project_management_system.dto.ForgotPasswordDTO;
import com.back_end.project_management_system.dto.RegisterUserDTO;
import com.back_end.project_management_system.entity.SecurityQuestion;
import com.back_end.project_management_system.entity.User;
import com.back_end.project_management_system.exception.AuthException;
import com.back_end.project_management_system.jwt.AuthenticationRequest;
import com.back_end.project_management_system.jwt.JWTUtil;

@Service
public class AuthService {
	
	@Autowired
	AuthDAO authDAO;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Transactional
	public com.back_end.project_management_system.entity.UserDetails userRegistration(RegisterUserDTO registrationData) {
		
		User user = new User(registrationData.getUsername(), registrationData.getPassword(), registrationData.getSecurityQuestion(), registrationData.getSecurityAnswer());
		
		user.setRole("EMPLOYEE");
		
		com.back_end.project_management_system.entity.UserDetails userDetails = new com.back_end.project_management_system.entity.UserDetails(user.getUsername(), registrationData.getName(), user.getRole());
		
		user.setUserDetails(userDetails);
		
		User newUser = authDAO.userRegistration(user);

		return newUser.getUserDetails();
	}
	
	@Transactional
	public Map<String, Object> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("User is Disabled", e);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentails", e);
		} catch(Exception e) {
			throw new Exception("Unknown Error", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtUtil.generateToken(userDetails);
		
		com.back_end.project_management_system.entity.UserDetails user = userDAO.validUser(userDetails.getUsername());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("username", user.getUsername());
		response.put("name", user.getName());
		response.put("token", token);
		response.put("role", user.getRole());
		return response;
	}
	
	@Transactional
	public String getForgotPasswordToken(ForgotPasswordDTO req) {
		
		String token = authDAO.getForgotPasswordToken(req);
		return token;
	}
	
	@Transactional
	public User validateUserByToken(String token) {
		
		Optional<User> userObject = authDAO.findUserByToken(token);
		
		if (!userObject.isPresent()) {
			throw new AuthException("Invalid token");
		}
		
		User user = userObject.get();
		
		if (user.getTokenExpiration().before(new Date(System.currentTimeMillis()))) {
			
			authDAO.deleteTokenFromUser(user);
			throw new AuthException("Invalid token");
		}
		
		return user;
	}
	
	@Transactional
	public boolean resetPassword(User user, String password) {
		
		User updatedUser = authDAO.resetPassword(user, password);
		
		if (updatedUser == null) {
			throw new AuthException("Password reset failed");
		}

		authDAO.deleteTokenFromUser(updatedUser);

		return true;
	}
	
	@Transactional
	public List<SecurityQuestion> getSecurityQuestions() {
		
		return authDAO.getSecurityQuestions();
	}

}
