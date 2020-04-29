package com.back_end.project_management_system.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back_end.project_management_system.entity.LinkedIssues;
import com.back_end.project_management_system.entity.LinkedIssuesPK;

public interface LinkedIssuesRepository extends JpaRepository<LinkedIssues, LinkedIssuesPK> {

}
