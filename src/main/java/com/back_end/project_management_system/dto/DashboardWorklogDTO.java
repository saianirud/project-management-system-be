package com.back_end.project_management_system.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class DashboardWorklogDTO {
	
	@NotNull(message = "Start date should not be null")
	private Date startDate;
	
	@NotNull(message = "End date should not be null")
	private Date endDate;
	
	@NotNull(message = "Username should not be null")
	private String username;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
