package com.back_end.project_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "security_questions")
public class SecurityQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@NotNull(message = "security question should not be null")
	private int id;
	
	@Column(name = "security_question")
	@NotNull(message = "security question should not be null")
	private String securityQuestion;
	
	public SecurityQuestion() {}

	public SecurityQuestion(String securityQuestion) {
		super();
		this.securityQuestion = securityQuestion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

}
