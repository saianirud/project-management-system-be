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
@Table(name = "issues")
@NamedEntityGraph(name = "issuesFetch", attributeNodes = {
		@NamedAttributeNode("issueType"),
		@NamedAttributeNode("issueCategory"),
		@NamedAttributeNode("issuePriority"),
		@NamedAttributeNode("issueReporter"),
		@NamedAttributeNode("issueAssignee")
})
public class Issue {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "issue_summary")
	private String issueSummary;
	
	@Column(name = "issue_description")
	private String issueDescription;
	
	@ManyToOne
	@JoinColumn(name = "issue_type")
	private IssueType issueType;
	
	@ManyToOne
	@JoinColumn(name = "issue_category")
	private IssueCategory issueCategory;
	
	@ManyToOne
	@JoinColumn(name = "issue_priority")
	private IssuePriority issuePriority;
	
	@ManyToOne
	@JoinColumn(name = "issue_reporter")
	private UserDetails issueReporter;
	
	@ManyToOne
	@JoinColumn(name = "issue_assignee")
	private UserDetails issueAssignee;
	
	@OneToMany(mappedBy = "issue", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"issue"})
	private List<WorkLog> workLogs;
	
	@Column(name = "project_key")
	private String projectKey;
	
	@Column(name = "original_estimate")
	private long originalEstimate;
	
	@Column(name = "logged_time")
	private long loggedTime;
	
	@OneToMany(mappedBy = "linkedIssuesPK.issueId", cascade = CascadeType.REMOVE)
	private List<LinkedIssues> linkedIssues;
	
	public Issue() {}

	public Issue(String issueSummary, String issueDescription, IssueType issueType, IssuePriority issuePriority) {
		super();
		this.issueSummary = issueSummary;
		this.issueDescription = issueDescription;
		this.issueType = issueType;
		this.issuePriority = issuePriority;
	}

	public Issue(String id, String issueSummary, IssueType issueType, IssueCategory issueCategory,
			IssuePriority issuePriority) {
		super();
		this.id = id;
		this.issueSummary = issueSummary;
		this.issueType = issueType;
		this.issueCategory = issueCategory;
		this.issuePriority = issuePriority;
	}

	public Issue(String id, String issueSummary, String issueDescription, IssueType issueType,
			IssueCategory issueCategory, IssuePriority issuePriority, UserDetails issueReporter,
			UserDetails issueAssignee, String projectKey, long originalEstimate, long loggedTime) {
		super();
		this.id = id;
		this.issueSummary = issueSummary;
		this.issueDescription = issueDescription;
		this.issueType = issueType;
		this.issueCategory = issueCategory;
		this.issuePriority = issuePriority;
		this.issueReporter = issueReporter;
		this.issueAssignee = issueAssignee;
		this.projectKey = projectKey;
		this.originalEstimate = originalEstimate;
		this.loggedTime = loggedTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIssueSummary() {
		return issueSummary;
	}

	public void setIssueSummary(String issueSummary) {
		this.issueSummary = issueSummary;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public IssuePriority getIssuePriority() {
		return issuePriority;
	}

	public void setIssuePriority(IssuePriority issuePriority) {
		this.issuePriority = issuePriority;
	}

	public UserDetails getIssueReporter() {
		return issueReporter;
	}

	public void setIssueReporter(UserDetails issueReporter) {
		this.issueReporter = issueReporter;
	}

	public UserDetails getIssueAssignee() {
		return issueAssignee;
	}

	public void setIssueAssignee(UserDetails issueAssignee) {
		this.issueAssignee = issueAssignee;
	}

	public long getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(long originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public long getLoggedTime() {
		return loggedTime;
	}

	public void setLoggedTime(long loggedTime) {
		this.loggedTime = loggedTime;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public IssueCategory getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(IssueCategory issueCategory) {
		this.issueCategory = issueCategory;
	}

	public List<WorkLog> getWorkLogs() {
		return workLogs;
	}

	public void setWorkLogs(List<WorkLog> workLogs) {
		this.workLogs = workLogs;
	}

	public List<LinkedIssues> getLinkedIssues() {
		return linkedIssues;
	}

	public void setLinkedIssues(List<LinkedIssues> linkedIssues) {
		this.linkedIssues = linkedIssues;
	}

}
