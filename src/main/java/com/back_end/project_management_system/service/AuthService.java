package com.back_end.project_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back_end.project_management_system.dao.AuthDAO;
import com.back_end.project_management_system.entity.SecurityQuestion;

@Service
public class AuthService {
	
	@Autowired
	AuthDAO authDAO;
	
	public String userRegistration() {
		
		return authDAO.userRegistration();
	}
	
	public List<SecurityQuestion> getSecurityQuestions() {
		
		return authDAO.getSecurityQuestions();
	}

}
