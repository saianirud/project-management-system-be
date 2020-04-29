package com.back_end.project_management_system.jpa_repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back_end.project_management_system.entity.IssueCategory;

public interface IssueCategoryRepository extends JpaRepository<IssueCategory, Integer> {
	
	public Optional<IssueCategory> findIssueCategoryByIssueCategory(String category);

}
