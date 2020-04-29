package com.back_end.project_management_system.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.back_end.project_management_system.entity.User;
import com.back_end.project_management_system.jpa_repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findById(username);
		
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return toUserDetails(user.get());
	}
	
	public UserDetails toUserDetails(User user) {
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(user.getRole())
				.build();
	}

}
