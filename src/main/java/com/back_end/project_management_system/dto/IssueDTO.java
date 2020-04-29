package com.back_end.project_management_system.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.back_end.project_management_system.entity.IssueCategory;
import com.back_end.project_management_system.entity.IssuePriority;
import com.back_end.project_management_system.entity.IssueType;
import com.back_end.project_management_system.regex.CommonRegex;

public class IssueDTO {

	@NotBlank(message = "Issue summary should not be null")
	@Size(max = 50, message = "Issu summary should be less than 50 characters")
	private String  issueSummary;
	
	@Size(max = 100, message = "Issue description should be less than 100 characters")
	private String issueDescription;
	
	@NotNull(message = "Issue type should not be null")
	private IssueType issueType;
	
	@NotNull(message = "Issue priority should not be null")
	private IssuePriority issuePriority;
	
	private IssueCategory issueCategory;
	
	@NotBlank(message = "Issue reporter should not be null")
	private String issueReporter;
	
	@NotBlank(message = "Issue assignee should not be null")
	private String issueAssignee;
	
	@NotBlank(message = "Project key should not be null")
	private String projectKey;

	@Pattern(regexp = CommonRegex.TIME_ESTIMATE_REGEX, message = "Invalid original estimate format. Eg: 2w 2d 3h 4m")
	private String originalEstimate;

	@Pattern(regexp = CommonRegex.TIME_ESTIMATE_REGEX, message = "Invalid logged time format. Eg: 2w 2d 3h 4m")
	private String loggedTime;

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

	public String getIssueReporter() {
		return issueReporter;
	}

	public void setIssueReporter(String issueReporter) {
		this.issueReporter = issueReporter;
	}

	public String getIssueAssignee() {
		return issueAssignee;
	}

	public void setIssueAssignee(String issueAssignee) {
		this.issueAssignee = issueAssignee;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(String originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public String getLoggedTime() {
		return loggedTime;
	}

	public void setLoggedTime(String loggedTime) {
		this.loggedTime = loggedTime;
	}

	public IssueCategory getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(IssueCategory issueCategory) {
		this.issueCategory = issueCategory;
	}
	
}
