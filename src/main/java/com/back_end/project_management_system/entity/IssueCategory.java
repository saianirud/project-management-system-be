package com.back_end.project_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issue_category")
public class IssueCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "issue_category")
	private String issueCategory;
	
	public IssueCategory() {}

	public IssueCategory(String issueCategory) {
		super();
		this.issueCategory = issueCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}

}
