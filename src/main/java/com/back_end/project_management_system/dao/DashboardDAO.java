package com.back_end.project_management_system.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.back_end.project_management_system.dto.DashboardAssignedIssuesDTO;
import com.back_end.project_management_system.dto.DashboardProjectsDTO;
import com.back_end.project_management_system.dto.DashboardWorklogDTO;
import com.back_end.project_management_system.dto.DashboardWorklogResponseDTO;
import com.back_end.project_management_system.entity.Issue;
import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.entity.WorkLog;
import com.back_end.project_management_system.service.UserService;

@Repository
public class DashboardDAO {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	UserService userService;
	
	public List<?> getWorklogSummary(DashboardWorklogDTO dashboardWorklogDTO) {
		
		UserDetails userDetails = userService.validUser(dashboardWorklogDTO.getUsername());
		
		String queryString = "SELECT DATE(logDateTime), SUM(timeSpent) FROM WorkLog WHERE loggedUser = :userDetails and logDateTime >= :startDate and logDateTime <= :endDate GROUP BY DATE(logDateTime)";
		
		Query query = entityManager.createQuery(queryString);
		
		query.setParameter("userDetails", userDetails);
		query.setParameter("startDate", dashboardWorklogDTO.getStartDate());
		query.setParameter("endDate", dashboardWorklogDTO.getEndDate());
		
		return query.getResultList();
	}
	
	public List<?> getProjectsSummary(DashboardProjectsDTO dashboardProjectsDTO) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
		
		Root<Issue> root = criteriaQuery.from(Issue.class);
		
		criteriaQuery.multiselect(root.get("projectKey"), root.get("issueCategory").get("issueCategory"), criteriaBuilder.count(root.get("id")));
		criteriaQuery.where(criteriaBuilder.equal(root.get("issueAssignee").get("username"), dashboardProjectsDTO.getUsername()));
		criteriaQuery.groupBy(root.get("projectKey"), root.get("issueCategory"));
		
		TypedQuery<?> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}
	
	public List<DashboardAssignedIssuesDTO> getAssignedIssues(DashboardProjectsDTO dashboardProjectsDTO) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DashboardAssignedIssuesDTO> criteriaQuery = criteriaBuilder.createQuery(DashboardAssignedIssuesDTO.class);
		
		Root<Issue> root = criteriaQuery.from(Issue.class);
		
		UserDetails userDetails = userService.validUser(dashboardProjectsDTO.getUsername());
		
		criteriaQuery.multiselect(root.get("id"), root.get("issueCategory"), root.get("issuePriority"), root.get("issueType"), root.get("projectKey"));
		criteriaQuery.where(criteriaBuilder.equal(root.get("issueAssignee"), userDetails));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("projectKey")));
		
		TypedQuery<DashboardAssignedIssuesDTO> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}
	
	public List<DashboardWorklogResponseDTO> getWorklogs(DashboardWorklogDTO dashboardWorklogDTO) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DashboardWorklogResponseDTO> criteriaQuery = criteriaBuilder.createQuery(DashboardWorklogResponseDTO.class);
		
		Root<WorkLog> root = criteriaQuery.from(WorkLog.class);
		
		UserDetails userDetails = userService.validUser(dashboardWorklogDTO.getUsername());
		
		Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("logDateTime"), dashboardWorklogDTO.getStartDate());
		Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("logDateTime"), dashboardWorklogDTO.getEndDate());
		Predicate userPredicate = criteriaBuilder.equal(root.get("loggedUser"), userDetails);
		
		Predicate finalPredicate = criteriaBuilder.and(userPredicate, startDatePredicate, endDatePredicate);
		
		criteriaQuery.multiselect(root.get("id"), root.get("timeSpent"), root.get("issue").get("id"), root.get("logDateTime"));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("logDateTime")));
		criteriaQuery.where(finalPredicate);
		
		TypedQuery<DashboardWorklogResponseDTO> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}

}
