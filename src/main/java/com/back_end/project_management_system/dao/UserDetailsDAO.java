package com.back_end.project_management_system.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.jpa_repository.UserDetailsRepository;

@Repository
public class UserDetailsDAO {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	public Optional<UserDetails> getUserByUsername(String username) {
		
		return userDetailsRepository.findUserDetailsByUsername(username);
	}

}
