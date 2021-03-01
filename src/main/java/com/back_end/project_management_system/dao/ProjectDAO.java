package com.back_end.project_management_system.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.back_end.project_management_system.dto.projectFilterDTO;
import com.back_end.project_management_system.entity.Issue;
import com.back_end.project_management_system.entity.Project;
import com.back_end.project_management_system.exception.ProjectException;
import com.back_end.project_management_system.jpa_repository.ProjectRepository;

@Repository
public class ProjectDAO {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public Project addProject(Project project) {
		
		Optional<Project> existingProject = projectRepository.findById(project.getProjectKey());
		
		if (existingProject.isPresent()) {
			throw new ProjectException("Project key must be unique");
		}
		
		Optional<Project> existingProject1 = projectRepository.findProjectByProjectName(project.getProjectName());
		
		if (existingProject1.isPresent()) {
			throw new ProjectException("Project name must be unique");
		}
		
		return saveProject(project);
	}
	
	public Project updateProject(Project project) {
		
		Optional<Project> existingProject = projectRepository.findById(project.getProjectKey());
		
		if (!existingProject.isPresent()) {
			throw new ProjectException("Project doesn't exist with key: " + project.getProjectKey());
		}
		
		Optional<Project> existingProject1 = projectRepository.findProjectByProjectName(project.getProjectName());
		
		if (existingProject1.isPresent()) {
			if (existingProject1.get().getProjectKey() != project.getProjectKey()) {
				throw new ProjectException("Project name must be unique");
			}
		}
		
		project.setLastIssueIndex(existingProject.get().getLastIssueIndex());
		project.setProjectType(existingProject.get().getProjectType());
		
		return saveProject(project);
	}
	
	public Project saveProject(Project project) {
		
		return projectRepository.save(project);
	}
	
	public Optional<Project> getProject(String projectKey) {
		
		return projectRepository.findById(projectKey);
	}
	
	public Project getProjectWithFilter(String projectKey, projectFilterDTO projectFilterDTO) {
		
		try {
			Project project = validProject(projectKey);
			
			EntityGraph<?> entityGraph = entityManager.getEntityGraph("issuesFetch");
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Issue> criteriaQuery = criteriaBuilder.createQuery(Issue.class);
			
			Root<Issue> root = criteriaQuery.from(Issue.class);
			
			Predicate assigneePredicate = criteriaBuilder.in(root.get("issueAssignee").get("username")).value(projectFilterDTO.getAssignees());
			Predicate projectKeyPredicate = criteriaBuilder.equal(root.get("projectKey"), project.getProjectKey());
			
			Predicate finalPredicate = criteriaBuilder.and(projectKeyPredicate, assigneePredicate);
			
			criteriaQuery.where(finalPredicate);
			
			TypedQuery<Issue> typedQuery = entityManager.createQuery(criteriaQuery);
			
			typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
			
			List<Issue> issues = typedQuery.getResultList();
			
			project.setIssues(issues);
			
			return project;
		} catch(NoResultException exception) {
			throw new ProjectException("Unable to fetch issues");
		} catch(Exception exception) {
			throw new ProjectException(exception.getMessage());
		}
	}
	
	public Project validProject(String projectKey) {
		
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			
			EntityGraph<?> entityGraph = entityManager.getEntityGraph("projectsFetch");
			
			CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
			
			Root<Project> root = criteriaQuery.from(Project.class);
			
			criteriaQuery.where(criteriaBuilder.equal(root.get("projectKey"), projectKey));
			
			TypedQuery<Project> typedQuery = entityManager.createQuery(criteriaQuery);
			
			typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
			
			return typedQuery.getSingleResult();

		} catch(NoResultException exception) {
			throw new ProjectException("Invalid project");
		} catch(Exception exception) {
			throw new ProjectException(exception.getMessage());
		}
		
	}
	
	public String deleteProject(String projectKey) {
		
		Optional<Project> existingProject = projectRepository.findById(projectKey);
		
		if (!existingProject.isPresent()) {
			throw new ProjectException("Project doesn't exist with key: " + projectKey);
		}
		
		projectRepository.deleteById(projectKey);
		
		return projectKey;
	}
	
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

}
