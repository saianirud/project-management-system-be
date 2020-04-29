package com.back_end.project_management_system.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back_end.project_management_system.entity.IssuePriority;

public interface IssuePriorityRepository extends JpaRepository<IssuePriority, Integer> {

}
