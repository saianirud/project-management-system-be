package com.back_end.project_management_system.dto;

import com.back_end.project_management_system.entity.IssueCategory;
import com.back_end.project_management_system.entity.IssuePriority;
import com.back_end.project_management_system.entity.IssueType;

public class DashboardAssignedIssuesDTO {

	private String id;
	private IssueCategory issueCategory;
	private IssuePriority issuePriority;
	private IssueType issueType;
	private String projectKey;
	
	public DashboardAssignedIssuesDTO() {}

	public DashboardAssignedIssuesDTO(String id, IssueCategory issueCategory, IssuePriority issuePriority,
			IssueType issueType, String projectKey) {
		super();
		this.id = id;
		this.issueCategory = issueCategory;
		this.issuePriority = issuePriority;
		this.issueType = issueType;
		this.projectKey = projectKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IssueCategory getIssueCategory() {
		return issueCategory;
	}
	public void setIssueCategory(IssueCategory issueCategory) {
		this.issueCategory = issueCategory;
	}
	public IssuePriority getIssuePriority() {
		return issuePriority;
	}
	public void setIssuePriority(IssuePriority issuePriority) {
		this.issuePriority = issuePriority;
	}
	public String getProjectKey() {
		return projectKey;
	}
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	
}
