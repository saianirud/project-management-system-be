package com.back_end.project_management_system.dto;

import javax.validation.constraints.NotBlank;

public class DashboardProjectsDTO {

	@NotBlank(message = "Username should not be null")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
