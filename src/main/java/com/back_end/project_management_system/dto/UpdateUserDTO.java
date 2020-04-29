package com.back_end.project_management_system.dto;

import javax.validation.constraints.NotBlank;

public class UpdateUserDTO {
	
	private String name;
	
	@NotBlank(message = "user role should not be null")
	private String role;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
