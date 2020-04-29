package com.back_end.project_management_system.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "user_details")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "role")
	private String role;
	
	@OneToMany(mappedBy = "projectLead")
	@JsonIgnoreProperties({"projectLead", "issues"})
	private List<Project> projects;
	
	@OneToMany(mappedBy = "issueReporter")
	@JsonIgnoreProperties({"issueReporter", "issueAssignee", "project", "workLogs"})
	private List<Issue> reportedIssues;

	@OneToMany(mappedBy = "issueAssignee")
	@JsonIgnoreProperties({"issueReporter", "issueAssignee", "project", "workLogs"})
	private List<Issue> assignedIssues;
	
	@OneToMany(mappedBy = "loggedUser")
	@JsonIgnoreProperties({"loggedUser"})
	private List<WorkLog> workLogs;
	
	public UserDetails() {}

	public UserDetails(String username, String name, String role) {
		super();
		this.username = username;
		this.name = name;
		this.role = role;
	}

	public UserDetails(int id, String username, String name, String role) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Issue> getReportedIssues() {
		return reportedIssues;
	}

	public void setReportedIssues(List<Issue> reportedIssues) {
		this.reportedIssues = reportedIssues;
	}

	public List<Issue> getAssignedIssues() {
		return assignedIssues;
	}

	public void setAssignedIssues(List<Issue> assignedIssues) {
		this.assignedIssues = assignedIssues;
	}

	public List<WorkLog> getWorkLogs() {
		return workLogs;
	}

	public void setWorkLogs(List<WorkLog> workLogs) {
		this.workLogs = workLogs;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
