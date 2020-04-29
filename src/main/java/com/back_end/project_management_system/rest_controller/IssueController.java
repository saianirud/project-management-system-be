package com.back_end.project_management_system.rest_controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.project_management_system.dto.IssueDTO;
import com.back_end.project_management_system.dto.LinkIssuesDTO;
import com.back_end.project_management_system.entity.Issue;
import com.back_end.project_management_system.entity.IssueCategory;
import com.back_end.project_management_system.entity.IssuePriority;
import com.back_end.project_management_system.entity.IssueType;
import com.back_end.project_management_system.entity.LinkedIssues;
import com.back_end.project_management_system.service.IssueService;

@RestController
@RequestMapping("/issues")
public class IssueController {
	
	@Autowired
	IssueService issueService;

	@GetMapping("/types")
	public List<IssueType> getAllIssueTypes() {
		return issueService.getAllIssueTypes();
	}
	
	@GetMapping("/priorities")
	public List<IssuePriority> getAllIssuePriorities() {
		return issueService.getAllIssuePriorities();
	}
	
	@GetMapping("/categories")
	public List<IssueCategory> getAllIssueCategories() {
		return issueService.getAllIssueCategories();
	}
	
	@PostMapping("")
	public ResponseEntity<?> addIssue(@Valid @RequestBody IssueDTO issueDTO) {
		
		Issue issue = issueService.addIssue(issueDTO);
		
		return ResponseEntity.ok(issue);
	}
	
	@PutMapping("/{issueId}")
	public ResponseEntity<?> updateIssue(@Valid @RequestBody IssueDTO issueDTO, @PathVariable String issueId) {
		
		Issue issue = issueService.updateIssue(issueDTO, issueId);
		
		return ResponseEntity.ok(issue);
		
	}
	
	@DeleteMapping("/{issueId}")
	public ResponseEntity<?> deleteIssue(@PathVariable String issueId) {
		
		String deletedIssueId = issueService.deleteIssue(issueId);
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("issueId", deletedIssueId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{issueId}")
	public ResponseEntity<?> getIssue(@PathVariable String issueId) {
		
		Issue issue = issueService.getIssue(issueId);
		
		return ResponseEntity.ok(issue);
	}
	
	@PostMapping("/linkIssues")
	public ResponseEntity<?> linkIssues(@Valid @RequestBody LinkIssuesDTO linkIssuesDTO) {
		
		List<LinkedIssues> linkedIssues = issueService.linkIssues(linkIssuesDTO);
		
		return ResponseEntity.ok(linkedIssues);
	}
	
	@PostMapping("/unlinkIssues")
	public ResponseEntity<?> unlinkIssues(@Valid @RequestBody LinkIssuesDTO linkIssuesDTO) {
		
		issueService.unlinkIssues(linkIssuesDTO);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "Issues detached successfully");
		
		return ResponseEntity.ok(map);
	}
	
}
