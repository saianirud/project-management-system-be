package com.back_end.project_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issue_priority")
public class IssuePriority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "issue_priority")
	private String issuePriority;
	
	@Column(name = "issue_priority_icon")
	private String issuePriorityIcon;
	
	public IssuePriority() {}

	public String getIssuePriorityIcon() {
		return issuePriorityIcon;
	}

	public void setIssuePriorityIcon(String issuePriorityIcon) {
		this.issuePriorityIcon = issuePriorityIcon;
	}

	public IssuePriority(String issuePriority, String issuePriorityIcon) {
		super();
		this.issuePriority = issuePriority;
		this.issuePriorityIcon = issuePriorityIcon;
	}

	public String getIssuePriority() {
		return issuePriority;
	}

	public void setIssuePriority(String issuePriority) {
		this.issuePriority = issuePriority;
	}

	public int getId() {
		return id;
	}

}
