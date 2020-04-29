package com.back_end.project_management_system.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "issue_dependency")
public class LinkedIssues {
	
	@EmbeddedId
	private LinkedIssuesPK linkedIssuesPK;
	
	public LinkedIssues() {}

	public LinkedIssues(LinkedIssuesPK linkedIssuesPK) {
		super();
		this.linkedIssuesPK = linkedIssuesPK;
	}

	public LinkedIssuesPK getLinkedIssuesPK() {
		return linkedIssuesPK;
	}

	public void setLinkedIssuesPK(LinkedIssuesPK linkedIssuesPK) {
		this.linkedIssuesPK = linkedIssuesPK;
	}

}
