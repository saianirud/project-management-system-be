package com.back_end.project_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back_end.project_management_system.dao.DashboardDAO;
import com.back_end.project_management_system.dto.DashboardAssignedIssuesDTO;
import com.back_end.project_management_system.dto.DashboardProjectsDTO;
import com.back_end.project_management_system.dto.DashboardWorklogDTO;
import com.back_end.project_management_system.dto.DashboardWorklogResponseDTO;

@Service
public class DashboardService {
	
	@Autowired
	DashboardDAO dashboardDAO;
	
	@Transactional
	public List<?> getWorklogSummary(DashboardWorklogDTO dashboardWorklogDTO) {
		
		return dashboardDAO.getWorklogSummary(dashboardWorklogDTO);
	}
	
	@Transactional
	public List<?> getProjectsSummary(DashboardProjectsDTO dashboardProjectsDTO) {
		
		return dashboardDAO.getProjectsSummary(dashboardProjectsDTO);
	}
	
	@Transactional
	public List<DashboardAssignedIssuesDTO> getAssignedIssues(DashboardProjectsDTO dashboardProjectsDTO) {
		
		return dashboardDAO.getAssignedIssues(dashboardProjectsDTO);
	}
	
	@Transactional
	public List<DashboardWorklogResponseDTO> getWorklogs(DashboardWorklogDTO dashboardWorklogDTO) {
		
		return dashboardDAO.getWorklogs(dashboardWorklogDTO);
	}

}
