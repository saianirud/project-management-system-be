package com.back_end.project_management_system.jpa_repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back_end.project_management_system.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

	public Optional<UserDetails> findUserDetailsByUsername(String username);
}
