package com.back_end.project_management_system.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "worklogs")
public class WorkLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "time_spent")
	private long timeSpent;
	
	@Column(name = "work_description")
	private String workDescription;
	
	@ManyToOne
	@JoinColumn(name = "logged_user")
	private UserDetails loggedUser;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "issue")
	@JsonIgnoreProperties({"workLogs", "linkedIssues"})
	private Issue issue;
	
	@Column(name = "log_date_time")
	private Date logDateTime;
	
	public WorkLog() {}

	public WorkLog(long timeSpent, String workDescription) {
		super();
		this.timeSpent = timeSpent;
		this.workDescription = workDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(long timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public UserDetails getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserDetails loggedUser) {
		this.loggedUser = loggedUser;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Date getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
	}

}
