package com.back_end.project_management_system.dto;

import java.util.List;

public class projectFilterDTO {

	private List<String> assignees;

	public List<String> getAssignees() {
		return assignees;
	}

	public void setAssignees(List<String> assignees) {
		this.assignees = assignees;
	}
}
