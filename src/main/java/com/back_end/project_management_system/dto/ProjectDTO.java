package com.back_end.project_management_system.dto;

import javax.validation.constraints.NotBlank;

public class ProjectDTO {
	
	@NotBlank(message = "Project name should not be null")
	private String projectName;
	
	@NotBlank(message = "Project key should not be null")
	private String projectKey;
	
	private String projectDescription;
	
	@NotBlank(message = "Project lead should not be null")
	private String projectLead;

	public ProjectDTO(String projectName, String projectKey, String projectDescription, String projectLead) {
		super();
		this.projectName = projectName;
		this.projectKey = projectKey;
		this.projectDescription = projectDescription;
		this.projectLead = projectLead;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectKey() {
		return projectKey;
	}
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getProjectLead() {
		return projectLead;
	}
	public void setProjectLead(String projectLead) {
		this.projectLead = projectLead;
	}

}
