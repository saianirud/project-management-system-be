package com.back_end.project_management_system.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class LinkIssuesDTO {

	@NotBlank(message = "Issue id should not be blank")
	private String issueId;
	
	@NotEmpty(message = "Linked issues should not be empty")
	private List<String> linkedIssues;

	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	public List<String> getLinkedIssues() {
		return linkedIssues;
	}
	public void setLinkedIssues(List<String> linkedIssues) {
		this.linkedIssues = linkedIssues;
	}
	
	
}
