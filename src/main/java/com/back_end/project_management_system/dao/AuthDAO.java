package com.back_end.project_management_system.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.back_end.project_management_system.dto.ForgotPasswordDTO;
import com.back_end.project_management_system.entity.SecurityQuestion;
import com.back_end.project_management_system.entity.User;
import com.back_end.project_management_system.exception.AuthException;
import com.back_end.project_management_system.jpa_repository.SecurityQuestionRepository;
import com.back_end.project_management_system.jpa_repository.UserRepository;

@Repository
public class AuthDAO {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SecurityQuestionRepository securityQuestionRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User userRegistration(User user) {
		
		Optional<User> existingUser = userRepository.findById(user.getUsername());
		
		if(existingUser.isPresent()) {
			throw new AuthException("User already exists with username: " + existingUser.get().getUsername());
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User newUser = userRepository.save(user);
		
		return newUser;
	}
	
	public String getForgotPasswordToken(ForgotPasswordDTO req) {
		
		String username = req.getUsername();
		int security_question_id = req.getSecurityQuestionId();
		String security_answer = req.getSecurityAnswer();
		
		Optional<User> userObject = userRepository.findById(username);
		
		if(!userObject.isPresent()) {
			throw new AuthException("User not found with username: " + username);
		}
		
		User user = userObject.get();
		
		if (security_question_id != user.getSecurityQuestion().getId() || !security_answer.toLowerCase().equals(user.getSecurityAnswer().toLowerCase())) {
			
			throw new AuthException("Invalid security question/answer");
		}
		
		String token = UUID.randomUUID().toString();
		
		user.setToken(token);
		user.setTokenExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000));
		
		userRepository.save(user);
		
		return token;
	}
	
	public Optional<User> findUserByToken(String token) {
		
		return userRepository.findUserByToken(token);
	}
	
	public void deleteTokenFromUser(User user) {
		
		user.setToken(null);
		user.setTokenExpiration(null);
		
		userRepository.save(user);
	}
	
	public User resetPassword(User user, String password) {
		
		user.setPassword(passwordEncoder.encode(password));
		
		User newUser = userRepository.save(user);
		
		return newUser;
	}
	
	public List<SecurityQuestion> getSecurityQuestions() {
		
		return securityQuestionRepository.findAll();
	}

}
