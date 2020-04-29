package com.back_end.project_management_system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LinkedIssuesPK implements Serializable {
	
	@Column(name = "issue_id")
	private String issueId;
	
	@Column(name = "dependent_issue_id")
	private String dependentIssueId;
	
	public LinkedIssuesPK() {}
	
	public LinkedIssuesPK(String issueId, String dependentIssueId) {
		super();
		this.issueId = issueId;
		this.dependentIssueId = dependentIssueId;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public String getDependentIssueId() {
		return dependentIssueId;
	}

	public void setDependentIssueId(String dependentIssueId) {
		this.dependentIssueId = dependentIssueId;
	}

}
