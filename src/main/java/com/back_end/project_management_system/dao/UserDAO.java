package com.back_end.project_management_system.dao;

import java.util.List;
import java.util.Optional;

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

import com.back_end.project_management_system.entity.User;
import com.back_end.project_management_system.entity.UserDetails;
import com.back_end.project_management_system.exception.ProjectException;
import com.back_end.project_management_system.jpa_repository.UserDetailsRepository;
import com.back_end.project_management_system.jpa_repository.UserRepository;

@Repository
public class UserDAO {
	
	@Autowired
	UserRepository userRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	public Optional<User> getUserByUsername(String username) {
		
		return userRepository.findById(username);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<UserDetails> getAllUsers() {
		return userDetailsRepository.findAll();
	}
	
	public void deleteUser(User user) {
		
		userRepository.delete(user);
	}
	
	public UserDetails validUser(String username) {
		
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<UserDetails> criteriaQuery = criteriaBuilder.createQuery(UserDetails.class);
			
			Root<UserDetails> root = criteriaQuery.from(UserDetails.class);
			
			criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));
			
			TypedQuery<UserDetails> typedQuery = entityManager.createQuery(criteriaQuery);
			
			return typedQuery.getSingleResult();

		} catch(NoResultException exception) {
			throw new ProjectException("Invalid issue");
		} catch(Exception exception) {
			throw new ProjectException(exception.getMessage());
		}
		
	}
	
	public List<UserDetails> getAllAdminsManagers() {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<UserDetails> criteriaQuery = criteriaBuilder.createQuery(UserDetails.class);
		
		Root<UserDetails> root = criteriaQuery.from(UserDetails.class);
		
		Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(root.get("role"), "ADMIN"), criteriaBuilder.equal(root.get("role"), "MANAGER"));
		
		criteriaQuery.multiselect(root.get("username"), root.get("name"), root.get("role"));
		criteriaQuery.where(predicate);
		
		TypedQuery<UserDetails> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}

}
