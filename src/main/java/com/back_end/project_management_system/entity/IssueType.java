package com.back_end.project_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issue_type")
public class IssueType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "issue_type")
	private String issueType;
	
	@Column(name = "issue_type_icon")
	private String issueTypeIcon;
	
	public IssueType() {}

	public IssueType(String issueType, String issueTypeIcon) {
		super();
		this.issueType = issueType;
		this.issueTypeIcon = issueTypeIcon;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public int getId() {
		return id;
	}

	public String getIssueTypeIcon() {
		return issueTypeIcon;
	}

	public void setIssueTypeIcon(String issueTypeIcon) {
		this.issueTypeIcon = issueTypeIcon;
	}
	
}
