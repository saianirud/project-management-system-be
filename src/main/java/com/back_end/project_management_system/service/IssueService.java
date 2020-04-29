package com.back_end.project_management_system.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back_end.project_management_system.dao.IssueDAO;
import com.back_end.project_management_system.dao.ProjectDAO;
import com.back_end.project_management_system.dao.UserDetailsDAO;
import com.back_end.project_management_system.dto.IssueDTO;
import com.back_end.project_management_system.entity.Issue;
import com.back_end.project_management_system.entity.IssueCategory;
import com.back_end.project_management_system.entity.IssuePriority;
import com.back_end.project_management_system.entity.IssueType;
import com.back_end.project_management_system.entity.Project;
import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.exception.ProjectException;
import com.back_end.project_management_system.util.IssueUtil;

@Service
public class IssueService {
	
	@Autowired
	IssueDAO issueDAO;
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@Autowired
	ProjectDAO projectDAO;
	
	@Autowired
	IssueUtil issueUtil;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;
	
	public List<IssueType> getAllIssueTypes() {
		return issueDAO.getAllIssueTypes();
	}
	
	public List<IssuePriority> getAllIssuePriorities() {
		return issueDAO.getAllIssuePriorities();
	}
	
	public List<IssueCategory> getAllIssueCategories() {
		return issueDAO.getAllIssueCategories();
	}
	
	@Transactional
	public Issue addIssue(IssueDTO issueDTO) {
		
		Issue issue = new Issue(issueDTO.getIssueSummary(), issueDTO.getIssueDescription(), issueDTO.getIssueType(), issueDTO.getIssuePriority());
		
		UserDetails issueReporter = userService.validUser(issueDTO.getIssueReporter());
		
		UserDetails issueAssignee = userService.validUser(issueDTO.getIssueAssignee());
		
		Project issueProject = projectService.validProject(issueDTO.getProjectKey());
		
		issue.setIssueReporter(issueReporter);
		issue.setIssueAssignee(issueAssignee);
		issue.setProject(issueProject);
		
		int index = issueProject.getLastIssueIndex() + 1;
		issue.setId(issueProject.getProjectKey() + '-' + index);
		
		Issue newIssue = issueDAO.addIssue(issue);
		
		issueProject.setLastIssueIndex(index);
		projectDAO.saveProject(issueProject);
		
		return newIssue;
	}
	
	@Transactional
	public Issue updateIssue(IssueDTO issueDTO, String issueId) {

		long originalEstimate = issueUtil.convertEstimatedTimeToMilliseconds(issueDTO.getOriginalEstimate());
		long loggedTime = issueUtil.convertEstimatedTimeToMilliseconds(issueDTO.getLoggedTime());
		
		Issue issue = new Issue(issueDTO.getIssueSummary(), issueDTO.getIssueDescription(), issueDTO.getIssueType(), issueDTO.getIssuePriority());
		
		UserDetails issueReporter = userService.validUser(issueDTO.getIssueReporter());
		
		UserDetails issueAssignee = userService.validUser(issueDTO.getIssueAssignee());
		
		Project issueProject = projectService.validProject(issueDTO.getProjectKey());
		
		issue.setId(issueId);
		issue.setIssueReporter(issueReporter);
		issue.setIssueAssignee(issueAssignee);
		issue.setIssueCategory(issueDTO.getIssueCategory());
		issue.setProject(issueProject);
		issue.setOriginalEstimate(originalEstimate);
		issue.setLoggedTime(loggedTime);

		return issueDAO.updateIssue(issue);
	}
	
	@Transactional
	public String deleteIssue(String issueId) {
		
		return issueDAO.deleteIssue(issueId);
	}
	
	@Transactional
	public Issue getIssue(String id) {
		
		Optional<Issue> issue = issueDAO.getIssueById(id);
		
		if (!issue.isPresent()) {
			throw new ProjectException("Issue doesn't exist with id: " + id);
		}
		
		return issue.get();
	}
	
	@Transactional
	public Issue validIssue(String id) {
		
		return issueDAO.validIssue(id);
	}

}
