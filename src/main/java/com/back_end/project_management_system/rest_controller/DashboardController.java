package com.back_end.project_management_system.rest_controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.project_management_system.dto.DashboardAssignedIssuesDTO;
import com.back_end.project_management_system.dto.DashboardProjectsDTO;
import com.back_end.project_management_system.dto.DashboardWorklogDTO;
import com.back_end.project_management_system.dto.DashboardWorklogResponseDTO;
import com.back_end.project_management_system.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	DashboardService dashboardService;

	@PostMapping("/worklogs/summary")
	public ResponseEntity<?> getWorklogSummary(@Valid @RequestBody DashboardWorklogDTO dashboardWorklogDTO) {
		
		List<?> worklogs =  dashboardService.getWorklogSummary(dashboardWorklogDTO);
		
		return ResponseEntity.ok(worklogs);
	}
	
	@PostMapping("/projects/summary")
	public ResponseEntity<?> getProjectsSummary(@Valid @RequestBody DashboardProjectsDTO dashboardProjectsDTO) {
		
		List<?> projectsSummary = dashboardService.getProjectsSummary(dashboardProjectsDTO);
		
		return ResponseEntity.ok(projectsSummary);
	}
	
	@PostMapping("/assignedissues")
	public ResponseEntity<?> getAssignedIssues(@Valid @RequestBody DashboardProjectsDTO dashboardProjectsDTO) {
		
		List<DashboardAssignedIssuesDTO> assignedIssues = dashboardService.getAssignedIssues(dashboardProjectsDTO);
		
		return ResponseEntity.ok(assignedIssues);
	}
	
	@PostMapping("/worklogs")
	public ResponseEntity<?> getWorklogs(@Valid @RequestBody DashboardWorklogDTO dashboardWorklogDTO) {
		
		List<DashboardWorklogResponseDTO> worklogs = dashboardService.getWorklogs(dashboardWorklogDTO);
		
		return ResponseEntity.ok(worklogs);
	}
	
}
