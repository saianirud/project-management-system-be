package com.back_end.project_management_system.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "projects")
@NamedEntityGraph(name = "projectsFetch", attributeNodes = {
			@NamedAttributeNode("projectLead")
		}
)
public class Project {
	
	@Id
	@Column(name = "project_key")
	private String projectKey;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_type")
	private String projectType;

	@Column(name = "project_description")
	private String projectDecription;
	
	@Column(name = "last_issue_index")
	private int lastIssueIndex;

	@ManyToOne
	@JoinColumn(name = "project_lead")
	private UserDetails projectLead;
	
	@OneToMany(mappedBy = "projectKey", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"workLogs", "projectKey", "linkedIssues"})
	private List<Issue> issues;
	
	public Project() {}

	public Project(String projectKey, String projectName, String projectDecription) {
		super();
		this.projectKey = projectKey;
		this.projectName = projectName;
		this.projectDecription = projectDecription;
	}

	public Project(String projectKey, String projectName, String projectType, String projectDecription,
			int lastIssueIndex, UserDetails projectLead) {
		super();
		this.projectKey = projectKey;
		this.projectName = projectName;
		this.projectType = projectType;
		this.projectDecription = projectDecription;
		this.lastIssueIndex = lastIssueIndex;
		this.projectLead = projectLead;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectDecription() {
		return projectDecription;
	}

	public void setProjectDecription(String projectDecription) {
		this.projectDecription = projectDecription;
	}

	public UserDetails getProjectLead() {
		return projectLead;
	}

	public void setProjectLead(UserDetails projectLead) {
		this.projectLead = projectLead;
	}

	public int getLastIssueIndex() {
		return lastIssueIndex;
	}

	public void setLastIssueIndex(int lastIssueIndex) {
		this.lastIssueIndex = lastIssueIndex;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
}
