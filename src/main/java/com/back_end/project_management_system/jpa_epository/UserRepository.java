package com.back_end.project_management_system.jpa_epository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back_end.project_management_system.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
