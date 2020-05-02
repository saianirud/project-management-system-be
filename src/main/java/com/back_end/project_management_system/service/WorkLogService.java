package com.back_end.project_management_system.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back_end.project_management_system.dao.WorkLogDAO;
import com.back_end.project_management_system.dto.WorkLogDTO;
import com.back_end.project_management_system.entity.Issue;
import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.entity.WorkLog;
import com.back_end.project_management_system.exception.ProjectException;
import com.back_end.project_management_system.jwt.JWTUtil;
import com.back_end.project_management_system.util.IssueUtil;

@Service
public class WorkLogService {
	
	@Autowired
	WorkLogDAO workLogDAO;
	
	@Autowired
	UserService userService;
	
	@Autowired
	IssueUtil issueUtil;
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Transactional
	public WorkLog addWorkLog(WorkLogDTO workLogDTO) {
		
		long timeSpent = issueUtil.convertEstimatedTimeToMilliseconds(workLogDTO.getTimeSpent());
		
		WorkLog workLog = new WorkLog(timeSpent, workLogDTO.getWorkDescription());
		
		Issue issue = issueService.validIssue(workLogDTO.getIssue());
		
		long loggedTime = issue.getLoggedTime() + timeSpent;
		
		issue.setLoggedTime(loggedTime);
		
		workLog.setIssue(issue);
		
		UserDetails user = userService.validUser(workLogDTO.getLoggedUser());
		
		workLog.setLoggedUser(user);
		
		workLog.setLogDateTime(workLogDTO.getLogDateTime());
		
		WorkLog newWorkLog = workLogDAO.addWorkLog(workLog);
		
		return newWorkLog;
	}
	
	@Transactional
	public WorkLog updateWorkLog(WorkLogDTO workLogDTO, int worklogId) {
		
		WorkLog workLog = workLogDAO.getWorkLog(worklogId);
		
		if (!workLog.getLoggedUser().getUsername().equals(workLogDTO.getLoggedUser())) {
			if (!userService.isAdmin(workLogDTO.getLoggedUser())) {
				throw new ProjectException(workLogDTO.getLoggedUser() + " doesn't have access to alter this worklog");
			}
		}
		
		long timeSpent = issueUtil.convertEstimatedTimeToMilliseconds(workLogDTO.getTimeSpent());
		
		long loggedTime = workLog.getIssue().getLoggedTime() - workLog.getTimeSpent() + timeSpent;
		Issue issue = workLog.getIssue();
		issue.setLoggedTime(loggedTime);
		
		workLog.setTimeSpent(timeSpent);
		workLog.setWorkDescription(workLogDTO.getWorkDescription());
		workLog.setLogDateTime(workLogDTO.getLogDateTime());
		
		WorkLog updatedWorkLog = workLogDAO.addWorkLog(workLog);
		
		return updatedWorkLog;
	}
	
	@Transactional
	public int deleteWorkLog(int worklogId, String jwtToken) {
		
		String username = jwtUtil.getUsernameFromToken(jwtToken);
		
		WorkLog workLog = workLogDAO.getWorkLog(worklogId);
		
		if (!workLog.getLoggedUser().getUsername().equals(username)) {
			if (!userService.isAdmin(username)) {
				throw new ProjectException(username + " doesn't have access to alter this worklog");
			}
		}
		
		long loggedTime = workLog.getIssue().getLoggedTime() - workLog.getTimeSpent();
		
		workLog.getIssue().setLoggedTime(loggedTime);
		
		return workLogDAO.deleteWorkLog(worklogId);
	}

}
