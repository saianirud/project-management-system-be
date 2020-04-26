package com.back_end.project_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back_end.project_management_system.dao.ProjectDAO;
import com.back_end.project_management_system.dao.UserDetailsDAO;
import com.back_end.project_management_system.dto.ProjectDTO;
import com.back_end.project_management_system.entity.Project;
import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.exception.AccessDeniedException;
import com.back_end.project_management_system.exception.ProjectException;

@Service
public class ProjectService {
	
	@Autowired
	ProjectDAO projectDAO;
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@Autowired
	UserService userService;

	public Project addProject(ProjectDTO projectData) {
		
		Project project = new Project(projectData.getProjectKey().toUpperCase(), projectData.getProjectName(), projectData.getProjectDescription());
		
		project.setLastIssueIndex(0);
		project.setProjectType( "Classic software");
		
		UserDetails user = userService.isAdminOrManager(projectData.getProjectLead());
		
		project.setProjectLead(user);
		
		return projectDAO.addProject(project);
	}
	
	public List<Project> getAllProjects() {
		return projectDAO.getAllProjects();
	}
	
	public Project validProject(String projectKey) {
		
		Optional<Project> project = projectDAO.getProject(projectKey);
		
		if (!project.isPresent()) {
			throw new ProjectException("Project doesn't exist with key: " + projectKey);
		}
		
		return project.get();
	}
	
	public Project updateProject(ProjectDTO projectDTO, String projectKey, String jwtToken) {
		
		Project project = validProject(projectKey);
		
		boolean isAuthorizedAdminOrManager = userService.isAuthorizedAdminOrManager(jwtToken, project.getProjectLead().getUsername());
		
		if (!isAuthorizedAdminOrManager) {
			throw new AccessDeniedException();
		}
		
		project.setProjectName(projectDTO.getProjectName());
		project.setProjectDecription(projectDTO.getProjectDescription());
		
		UserDetails user = userService.isAdminOrManager(projectDTO.getProjectLead());
		
		project.setProjectLead(user);
		
		return projectDAO.updateProject(project);
	}
	
	public String deleteProject(String projectKey, String jwtToken) {
		
		Project project = validProject(projectKey);
		
		boolean isAuthorizedAdminOrManager = userService.isAuthorizedAdminOrManager(jwtToken, project.getProjectLead().getUsername());
		
		if (!isAuthorizedAdminOrManager) {
			throw new AccessDeniedException();
		}
		
		return projectDAO.deleteProject(projectKey);
	}

}
