package com.back_end.project_management_system.dto;

import com.back_end.project_management_system.entity.UserDetails;

public class AllProjectsDTO {

	private String projectKey;

	private String projectName;

	private String projectType;

	private String projectDecription;

	private UserDetails projectLead;
	
	public AllProjectsDTO() {}

	public AllProjectsDTO(String projectKey, String projectName, String projectType, String projectDecription, UserDetails projectLead) {
		super();
		this.projectKey = projectKey;
		this.projectName = projectName;
		this.projectType = projectType;
		this.projectDecription = projectDecription;
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

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectDecription() {
		return projectDecription;
	}

	public void setProjectDecription(String projectDecription) {
		this.projectDecription = projectDecription;
	}

	public UserDetails getProjectLead() {
		return projectLead;
	}

	public void setProjectLead(UserDetails projectLead) {
		this.projectLead = projectLead;
	}
}
