package com.back_end.project_management_system.dto;

import java.util.Date;

public class DashboardWorklogResponseDTO {
	
	private int id;
	private long timeSpent;
	private String issueId;
	private Date logDateTime;
	
	DashboardWorklogResponseDTO() {}

	public DashboardWorklogResponseDTO(int id, long timeSpent, String issueId, Date logDateTime) {
		super();
		this.id = id;
		this.timeSpent = timeSpent;
		this.issueId = issueId;
		this.logDateTime = logDateTime;
	}

	public long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(long timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public Date getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
