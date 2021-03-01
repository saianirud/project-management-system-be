package com.back_end.project_management_system.jpa_repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.back_end.project_management_system.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	@EntityGraph(value = "projectsFetch", type = EntityGraphType.FETCH)
	public List<Project> findAll();
	
	public Optional<Project> findProjectByProjectName(String name);
}
