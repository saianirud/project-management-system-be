package com.back_end.project_management_system.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.back_end.project_management_system.regex.CommonRegex;

public class WorkLogDTO {

	@NotBlank(message = "Time spent should not be null")
	@Pattern(regexp = CommonRegex.TIME_ESTIMATE_REGEX, message = "Invalid time spent format. Eg: 2w 2d 3h 4m")
	private String timeSpent;
	
	private String workDescription;
	
	@NotBlank(message = "Logging User should not be null")
	private String loggedUser;
	
	@NotNull(message = "Log Date time should not be null")
	private Date logDateTime;
	
	@NotBlank(message = "Corresponding issue should not be null")
	private String issue;

	public String getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}
	public String getWorkDescription() {
		return workDescription;
	}
	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
	public String getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public Date getLogDateTime() {
		return logDateTime;
	}
	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
	}
	
}
