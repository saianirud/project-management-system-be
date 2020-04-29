package com.back_end.project_management_system.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.back_end.project_management_system.entity.Issue;
import com.back_end.project_management_system.entity.IssueCategory;
import com.back_end.project_management_system.entity.IssuePriority;
import com.back_end.project_management_system.entity.IssueType;
import com.back_end.project_management_system.entity.LinkedIssues;
import com.back_end.project_management_system.exception.ProjectException;
import com.back_end.project_management_system.jpa_repository.IssueCategoryRepository;
import com.back_end.project_management_system.jpa_repository.IssuePriorityRepository;
import com.back_end.project_management_system.jpa_repository.IssueRepository;
import com.back_end.project_management_system.jpa_repository.IssueTypeRepository;
import com.back_end.project_management_system.jpa_repository.LinkedIssuesRepository;
import com.back_end.project_management_system.jpa_repository.ProjectRepository;

@Repository
public class IssueDAO {
	
	@Autowired
	IssueTypeRepository issueTypeRepository;
	
	@Autowired
	IssuePriorityRepository issuePriorityRepository;
	
	@Autowired
	IssueRepository issueRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	IssueCategoryRepository issueCategoryRepository;
	
	@Autowired
	LinkedIssuesRepository linkedIssuesRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<IssueType> getAllIssueTypes() {
		return issueTypeRepository.findAll();
	}
	
	public List<IssuePriority> getAllIssuePriorities() {
		return issuePriorityRepository.findAll();
	}
	
	public List<IssueCategory> getAllIssueCategories() {
		return issueCategoryRepository.findAll();
	}
	
	public Issue addIssue(Issue issue) {
		
		Optional<IssueCategory> issueCategory = issueCategoryRepository.findIssueCategoryByIssueCategory("BACKLOG");
		
		issue.setIssueCategory(issueCategory.get());

		return saveIssue(issue);
	}
	
	public Issue updateIssue(Issue issue) {
		
		Optional<Issue> existingIssue = getIssueById(issue.getId());
		
		if (!existingIssue.isPresent()) {
			throw new ProjectException("Issue doesn't exist with id: " + issue.getId());
		}
		
		return saveIssue(issue);
	}
	
	public Issue saveIssue(Issue issue) {
		
		return issueRepository.save(issue);
	}
	
	public String deleteIssue(String issueId) {
		
		Optional<Issue> existingIssue = getIssueById(issueId);
		
		if (!existingIssue.isPresent()) {
			throw new ProjectException("Issue doesn't exist with id: " + issueId);
		}
		
		issueRepository.deleteById(issueId);
		
		return issueId;
	}
	
	public Optional<Issue> getIssueById(String id) {
		
		return issueRepository.findById(id);
	}
	
	public Issue validIssue(String id) {
		
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
			
			Root<Issue> root = criteriaQuery.from(Issue.class);
			
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
			
			TypedQuery<Issue> typedQuery = entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getSingleResult();

		} catch(NoResultException exception) {
			throw new ProjectException("Invalid issue");
		} catch(Exception exception) {
			throw new ProjectException(exception.getMessage());
		}
		
	}
	
	public List<Issue> findAllIssuesById(List<String> ids) {
		
		return issueRepository.findAllById(ids);
	}
	
	public List<LinkedIssues> linkIssues(List<LinkedIssues> linkedIssues) {
		
		return linkedIssuesRepository.saveAll(linkedIssues);
	}
	
	public void unlinkIssues(List<LinkedIssues> linkedIssues) {
		
		linkedIssuesRepository.deleteAll(linkedIssues);
	}

}
