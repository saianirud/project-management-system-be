package com.back_end.project_management_system.rest_controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.project_management_system.dto.AllProjectsDTO;
import com.back_end.project_management_system.dto.ProjectDTO;
import com.back_end.project_management_system.dto.projectFilterDTO;
import com.back_end.project_management_system.entity.Project;
import com.back_end.project_management_system.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;

	@PostMapping("")
	public ResponseEntity<?> addProject(@Valid @RequestBody ProjectDTO projectData) {
		
		Project project = projectService.addProject(projectData);
		
		return ResponseEntity.ok(project);
	}
	
	@PutMapping("/{projectKey}")
	public ResponseEntity<?> updateProject(@Valid @RequestBody ProjectDTO projectDTO, @PathVariable String projectKey, HttpServletRequest request) {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		
		Project project = projectService.updateProject(projectDTO, projectKey, jwtToken);
		
		return ResponseEntity.ok(project);
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllProjects() {
		
		List<AllProjectsDTO> projects = projectService.getAllProjects();
		
		return ResponseEntity.ok(projects);
	}
	
	@DeleteMapping("/{projectKey}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectKey, HttpServletRequest request) {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = requestTokenHeader.substring(7);
		
		String id = projectService.deleteProject(projectKey, jwtToken);
		
		Map<String, String> response = new HashMap<String, String>();
		
		response.put("projectKey", id);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/{projectKey}")
	public ResponseEntity<?> getProject(@PathVariable String projectKey, @RequestBody projectFilterDTO projectFilterDTO) {
	
		Project project = projectService.getProject(projectKey, projectFilterDTO);
		
		return ResponseEntity.ok(project);
	}
}
