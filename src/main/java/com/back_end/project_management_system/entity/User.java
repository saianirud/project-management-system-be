package com.back_end.project_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "security_question_id")
	private int securityQuestionId;
	
	@Column(name = "security_answer")
	private String securityAnswer;
	
	public User() {}

	public User(String userName, String password, String name, int securityQuestionId, String securityAnswer) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.securityQuestionId = securityQuestionId;
		this.securityAnswer = securityAnswer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSecurityQuestionId() {
		return securityQuestionId;
	}

	public void setSecurityQuestionId(int securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

}
