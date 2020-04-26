package com.back_end.project_management_system.jpa_repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back_end.project_management_system.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findUserByToken(String token);

}
