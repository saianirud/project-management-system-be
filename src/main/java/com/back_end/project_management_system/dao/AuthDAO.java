package com.back_end.project_management_system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.back_end.project_management_system.entity.SecurityQuestion;
import com.back_end.project_management_system.jpa_epository.SecurityQuestionReository;

@Repository
public class AuthDAO {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	SecurityQuestionReository securityQuestionReository;
	
	public String userRegistration() {
		
		return "Hi";
	}
	
	public List<SecurityQuestion> getSecurityQuestions() {
		
		return securityQuestionReository.findAll();
	}

}
