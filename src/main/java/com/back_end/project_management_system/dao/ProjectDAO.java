package com.back_end.project_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.back_end.project_management_system.entity.Project;
import com.back_end.project_management_system.exception.ProjectException;
import com.back_end.project_management_system.jpa_repository.ProjectRepository;

@Repository
public class ProjectDAO {
	
	@Autowired
	ProjectRepository projectRepository;
	
	public Project addProject(Project project) {
		
		Optional<Project> existingProject = projectRepository.findById(project.getProjectKey());
		
		if (existingProject.isPresent()) {
			throw new ProjectException("Project key must be unique");
		}
		
		Optional<Project> existingProject1 = projectRepository.findProjectByProjectName(project.getProjectName());
		
		if (existingProject1.isPresent()) {
			throw new ProjectException("Project name must be unique");
		}
		
		return projectRepository.save(project);
	}
	
	public Project updateProject(Project project) {
		
		Optional<Project> existingProject = projectRepository.findById(project.getProjectKey());
		
		if (!existingProject.isPresent()) {
			throw new ProjectException("Project doesn't exist with key: " + project.getProjectKey());
		}
		
		Optional<Project> existingProject1 = projectRepository.findProjectByProjectName(project.getProjectName());
		
		if (existingProject1.isPresent()) {
			if (existingProject1.get().getProjectKey() != project.getProjectKey()) {
				throw new ProjectException("Project name must be unique");
			}
		}
		
		project.setLastIssueIndex(existingProject.get().getLastIssueIndex());
		project.setProjectType(existingProject.get().getProjectType());
		
		return projectRepository.save(project);
	}
	
	public Optional<Project> getProject(String projectKey) {
		
		return projectRepository.findById(projectKey);
	}
	
	public String deleteProject(String projectKey) {
		
		Optional<Project> existingProject = projectRepository.findById(projectKey);
		
		if (!existingProject.isPresent()) {
			throw new ProjectException("Project doesn't exist with key: " + projectKey);
		}
		
		projectRepository.deleteById(projectKey);
		
		return projectKey;
	}
	
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

}
